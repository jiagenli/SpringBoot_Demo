package com.ljg.learn.es.log.aop;

import com.ljg.learn.es.log.annotation.SystemLog;
import com.ljg.learn.es.log.log.EsLog;
import com.ljg.learn.es.log.service.EsLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 日志管理
 */
@Aspect
@Component
@Slf4j
public class SystemLogAspect {
    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<>("ThreadLocal beginTime");

    @Autowired
    private EsLogService esLogService;

    @Autowired
    private HttpServletRequest request;

    /**
     * Controller层切点,注解方式
     */
    @Pointcut("@annotation(com.ljg.learn.es.log.annotation.SystemLog)")
    public void controllerAspect() {
    }

    /**
     * 前置通知
     * @param joinPoint
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        Date beginTime = new Date();
        beginTimeThreadLocal.set(beginTime);
    }

    /**
     * 后置通知
     * @param joinPoint
     */
    @AfterReturning("controllerAspect()")
    public void after(JoinPoint joinPoint) throws Exception {
        String username = "";
        String description = getControllerMethodInfo(joinPoint).get("description").toString();
        int type = (int) getControllerMethodInfo(joinPoint).get("type");
        Map<String, String[]> logParams = request.getParameterMap();
        EsLog esLog = new EsLog();

        esLog.setUsername("test_user");
        esLog.setName(description);
        esLog.setLogType(type);
        esLog.setRequestUrl(request.getRequestURI());
        esLog.setRequestType(request.getMethod());
        esLog.setMapToParams(logParams);

        long beginTime = beginTimeThreadLocal.get().getTime();
        long endTime = System.currentTimeMillis();

        Long logElapsedTime = endTime - beginTime;
        esLog.setCostTime(logElapsedTime.intValue());

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new SaveEsSystemLogThread(esLog, esLogService));
    }

    /**
     * 保存日志到ES
     */
    private static class SaveEsSystemLogThread implements Runnable {
        private EsLog eslog;
        private EsLogService esLogService;

        public SaveEsSystemLogThread(EsLog esLog, EsLogService esLogService) {
            this.eslog = esLog;
            this.esLogService = esLogService;
        }

        @Override
        public void run() {
            log.info(eslog.toString());
            esLogService.saveLog(eslog);
        }
    }

    /**
     * 获取注解中对方法的描述信息，用于Controller注解
     */
    public static Map<String, Object> getControllerMethodInfo(JoinPoint joinPoint) throws Exception {
        Map<String, Object> map = new HashMap<>(16);
        // 获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();

        String description = "";
        Integer type = null;

        for (Method method : methods) {
            if (!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if (clazzs.length != arguments.length) {
                continue;
            }
            description = method.getAnnotation(SystemLog.class).description();
            type = method.getAnnotation(SystemLog.class).type().ordinal();
            map.put("description", description);
            map.put("type", type);
        }
        return map;
    }
}

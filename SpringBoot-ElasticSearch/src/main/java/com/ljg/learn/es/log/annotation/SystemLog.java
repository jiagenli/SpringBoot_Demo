package com.ljg.learn.es.log.annotation;

import com.ljg.learn.es.log.enums.LogType;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    /**
     * 日志名称
     * @return
     */
    String description() default "";

    /**
     * 日志类型
     * @return
     */
    LogType type() default LogType.OPERATION;
}

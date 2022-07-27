package com.ljg.learn.es.log.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * <a href="https://www.cnblogs.com/niceyoo/p/12969341.html">...</a>
 */
@Data
@Document(indexName = "log")
public class EsLog implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id = UUID.randomUUID().toString();

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(type = FieldType.Date, index = false, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime = new Date();

    /**
     * 时间戳
     */
    private Long timeMills = System.currentTimeMillis();

    /**
     * 方法操作名称
     */
    private String name;

    /**
     * 日志类型
     */
    private Integer logType;

    /**
     * 请求链接
     */
    private String requestUrl;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求用户
     */
    private String username;

    /**
     * ip
     */
    private String ip;

    /**
     * 花费时间
     */
    private Integer costTime;

    /**
     * 转换请求参数为Json
     * @param paramMap
     */
    public void setMapToParams(Map<String, String[]> paramMap) {
        this.requestParam = paramMap.toString();
    }
}

package com.ljg.learn.cache.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
@TableName("sys_user")
public class User implements Serializable {

    @TableId
    private String userId;

    private String username;
}

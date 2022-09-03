package com.ljg.learn.redis.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class User implements Serializable {

    private String userId;

    private String userName;
}

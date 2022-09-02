package com.ljg.learn.redis.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private String userId;

    private String userName;
}

package com.ljg.learn.cache.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljg.learn.cache.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

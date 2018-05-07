package liul.cn.shiro.mapper;

import org.apache.ibatis.annotations.Param;

import liul.cn.shiro.model.User;

public interface UserMapper {

    User findByUsername(@Param("username") String username);
}


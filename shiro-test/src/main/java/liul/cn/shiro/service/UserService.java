package liul.cn.shiro.service;

import liul.cn.shiro.model.User;

public interface UserService {
    User findByUsername(String username);
}

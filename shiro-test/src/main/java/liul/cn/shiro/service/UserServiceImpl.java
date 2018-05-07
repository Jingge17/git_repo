package liul.cn.shiro.service;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import liul.cn.shiro.mapper.UserMapper;
import liul.cn.shiro.model.User;

@Service
public class UserServiceImpl  implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}

package liul.cn.test.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import liul.cn.test.dao.UserMapper;
/**
 * http://localhost/javaweb_test_7_druid_logback/sys/druid/login.html  druid页面
 * http://localhost/javaweb_test_7_druid_logback/test    测试页面
 * 
 */
@Controller
public class UserController {
    
	@Resource
	UserMapper UserMapper;

    @RequestMapping("/test")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	System.out.println(UserMapper.selectByPrimaryKey(1));
        response.sendRedirect("index.jsp");
    }
}

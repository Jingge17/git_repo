package com.shiro.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.shiro.Utils.EncryptUtils;
import com.shiro.model.User;

@Controller
@RequestMapping(value = "login")
public class LoginController {
	/*
	 * @Autowired User user;
	 */
	/**
	 * 用户登录
	 * 
	 * @param user
	   *           　登录用户
	 * @return
	 */
	@RequestMapping(params = "main")
	public ModelAndView login(User user,HttpSession session, HttpServletRequest request) {

		ModelAndView modelView = new ModelAndView();
		Subject currentUser = SecurityUtils.getSubject();
		//登录token   Realm 的doGetAuthenticationInfo方法中设置token
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsercode(), EncryptUtils.encryptMD5(user.getPassword()));
		token.setRememberMe(true);
		try {
			//登录token和realm中的token配对
			currentUser.login(token);
		} catch (AuthenticationException e) {
			modelView.addObject("message", "login errors");
			modelView.setViewName("/login");
			e.printStackTrace();
			
		}
		if(currentUser.isAuthenticated()){//配对成功
			user.setUserName("张三");
			session.setAttribute("userinfo", user);
			modelView.setViewName("/main");
		}else{
			modelView.addObject("message", "login errors");
			modelView.setViewName("/login");
		}
		return modelView;
	}

	/**
	 * 退出登录
	 * 
	 * @return
	 */
	@RequestMapping(params = "logout")
	public String logout() {
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.logout();//注销token	
		} catch (AuthenticationException e) {
			e.printStackTrace();

		}
		return "/login";
	}

	@RequestMapping(params = "myjsp")
	public ModelAndView login2() {
		
		System.out.println("sss");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("message", "登录成功!");
		modelView.setViewName("/my");
		return modelView;
	}

	@RequestMapping(params = "test")
	public ModelAndView login3() {
		System.out.println("sss");
		ModelAndView modelView = new ModelAndView();
		modelView.addObject("message", "登录成功!");
		modelView.setViewName("/test");
		return modelView;
	}
}

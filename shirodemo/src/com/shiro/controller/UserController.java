package com.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="user")
public class UserController {
	/**
	 * 跳转到myjsp页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "myjsp")
	public String home() {
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isPermitted("user.do?myjsp")){//用户权限匹配   会执行realm中的doGetAuthorizationInfo方法获取权限
			return "my";
		}else{
			return "error/noperms";
		}
	}
	@RequestMapping(params = "notmyjsp")
	public String nopermission() {
		System.out.println("notmyjsp");
		Subject currentUser = SecurityUtils.getSubject();
		if(currentUser.isPermitted("user.do?notmyjsp")){//此处权限匹配失败跳转spring-shiro.xml配置的 name="unauthorizedUrl"设置的页面中
			return "notmyjsp";//不会被执行
		}else{
			return "error/noperms";
		}
	}
}

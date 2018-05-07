package liul.cn.security;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration//根据导入jar包自动导入相关bean
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解，用于控制不同权限用户对控制层的访问
public class SecurityTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityTestApplication.class, args);
	}
	
	@RequestMapping("/")
	public String home() {
		return "hello spring boot";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello world";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")//权限角色是admin才能访问
	@RequestMapping("/roleAuth")
	public String role() {
		return "admin auth";
	}


	@PreAuthorize("#id<10 and principal.username.equals(#username) and #user.username.equals('abc')")
	@PostAuthorize("returnObject%2==0")
	@RequestMapping("/test")
	public Integer test(Integer id, String username, User user) {
		// ...
		return id;
	}

	@PreFilter("filterObject%2==0")
	@PostFilter("filterObject%4==0")
	@RequestMapping("/test2")
	public List<Integer> test2(List<Integer> idList) {
		// ...
		return idList;
	}
}

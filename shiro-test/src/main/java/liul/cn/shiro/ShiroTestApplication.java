package liul.cn.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan
@MapperScan(basePackages = {"liul.cn.shiro.mapper"})
@SpringBootApplication
public class ShiroTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShiroTestApplication.class, args);
	}
}

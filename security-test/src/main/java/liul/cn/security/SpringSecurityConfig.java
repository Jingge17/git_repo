package liul.cn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
	
@Configuration
@EnableWebSecurity  //@EnableWebSecurity要配合WebSecurityConfigurerAdapter使用
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserService myUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {//设置用户认证
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");//固定的身份认证
//        auth.inMemoryAuthentication().withUser("zhangsan").password("zhangsan").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("demo").password("demo").roles("USER");

        auth.userDetailsService(myUserService).passwordEncoder(new MyPasswordEncoder());//基于数据库使用security提供表进行验证（这里没用数据库）
//        auth.jdbcAuthentication().usersByUsernameQuery("select username,password,enabled from users where username = ?").authoritiesByUsernameQuery("select username,authority from authorities where username = ?").passwordEncoder(new MyPasswordEncoder()); //基于数据库自定义表进行用户匹配
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .and()
                .formLogin();
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {//放行
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }
}
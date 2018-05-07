package liul.cn.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class MyUserService implements UserDetailsService{
/*
 * loadUserByUsername是通过登录进来的用户名进行判断（一般查询数据库），最后返回User对象用于对登录的用户名和密码进行效验和效验成功的赋权
 * 
 */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	System.out.println("liul.cn.security.MyUserService.loadUserByUsername(String)-->"+username);
    	Collection<GrantedAuthority> grantedAuths ;
    	Set<GrantedAuthority> authSet=new HashSet<>();
    	authSet.add(new SimpleGrantedAuthority("ROLE_ADMIN"));//权限要以ROLE_开头
    	grantedAuths=authSet;
        return new User("123","123",true,true,true,true,grantedAuths);//这是返回数据库的用户信息
    }
}

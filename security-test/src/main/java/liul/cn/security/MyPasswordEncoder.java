package liul.cn.security;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {

    private final static String SALT = "123456";
    @Override
    public String encode(CharSequence rawPassword) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        String encoderpwd=encoder.encodePassword(rawPassword.toString(), SALT);
        System.out.println("liul.cn.security.MyPasswordEncoder.encode(CharSequence)-->"+encoderpwd);
        return encoderpwd;
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        System.out.println("liul.cn.security.MyPasswordEncoder.matches(CharSequence, String)-->encodedPassword:"+encodedPassword+" , rawPassword:"+rawPassword.toString());
        return encoder.isPasswordValid(encoder.encodePassword(encodedPassword.toString(), SALT), rawPassword.toString(), SALT);//这里放的是加密后的密码(从实现UserDetailsService的loadUserByUsername传过来的)和未加密(用户输入)的密码，查看密码是否有效.
    }
    
	public static void main(String[] args) {
		System.out.println(new MyPasswordEncoder().encode("123"));//123加密后是b782af0b0dd80977678576f7db0f9351
		System.out.println(new Md5PasswordEncoder().isPasswordValid("b782af0b0dd80977678576f7db0f9351", "123", SALT));//true
	}
}
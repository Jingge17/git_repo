package liul.cn.test.utils;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import liul.cn.test.beans.Mail;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MailUtil {
	 /** 163邮箱*/
                  
    public static boolean send(Mail mail) {
        String from = "m17805931048@163.com";
        int port = 25;
        String host = "smtp.163.com";
        String pass = "这里填密码";
        String nickname = "sertest";

        HtmlEmail email = new HtmlEmail();
        try {
            email.setHostName(host);
            email.setCharset("UTF-8");
            for (String str : mail.getReceivers()) {
                email.addTo(str);
            }
            email.setFrom(from, nickname);
            email.setSmtpPort(port);
            email.setAuthentication(from, pass);
            email.setSubject(mail.getSubject());
            email.setMsg(mail.getMessage());
            email.send();
            log.info("{} 发送邮件到 {}", from, StringUtils.join(mail.getReceivers(), ","));
            return true;
        } catch (EmailException e) {
            log.error(from + "发送邮件到" + StringUtils.join(mail.getReceivers(), ",") + "失败", e);
            return false;
        }
    }
    
    public static void main(String args[]) {
    	Mail mail=new Mail();
    	Set<String> mset=new HashSet<>();
    	mset.add("444563118@qq.com");
    	mail.setMessage("测试发送邮件测试发送邮件测试发送邮件测试发送邮件测试发送邮件测试发送邮件测试发送邮件测试发送邮件测试发送邮件测试发送邮件测试发送邮件");
    	mail.setSubject("TestTest");
    	mail.setReceivers(mset);
    	System.out.println(send(mail));
    }

    
    
}


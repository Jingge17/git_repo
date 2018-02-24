package cn.liul.provider;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * spring加载时候将带有@RpcService注解的类和其接口存放到annoMap表中
 */
@Component
public class SpringCtxInit implements ApplicationContextAware, InitializingBean {
	Class<?> interfaceValue=null;
	Class<?> classValue=null;
	public static Map<Class<?>,Class<?>> annoMap=new HashMap<Class<?>, Class<?>>();//存放接口，实现类表
	//获取添加@RpcService注解的类名
	public void setApplicationContext(ApplicationContext ctx) throws BeansException {
		Map<String, Object> beanMap = ctx.getBeansWithAnnotation(RpcService.class);
        for(Object bean:beanMap.values()) {
        	classValue=bean.getClass();
        	interfaceValue=bean.getClass().getAnnotation(RpcService.class).value1();
        	String value2=bean.getClass().getAnnotation(RpcService.class).value2();
        	annoMap.put(interfaceValue, classValue);
        	System.out.println("class:"+classValue+"  interface:"+interfaceValue+"  abc:"+value2+"\n");
        }
	}
	
	public static Map<Class<?>, Class<?>> getAnnoMap(){
       return annoMap;
	}

	public void afterPropertiesSet() throws Exception {
		// TODO 如果要做类似dubbo的rpc，这里可以初始化zookeeper和netty
		
	}

}

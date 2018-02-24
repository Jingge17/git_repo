package cn.liul.provider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class ServiceTest {
    /**
     * 根据annoMap表中的接口获取实现类，从而构造代理类
     */
    public Object getService(Class<?> inteface) {
    	final Class<?> classValue=SpringCtxInit.getAnnoMap().get(inteface);
    	return Proxy.newProxyInstance(inteface.getClassLoader(), new Class<?>[] {inteface}, new InvocationHandler() {

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("proxy method:  "+method);
				// TODO 如果要做类似dubbo的rpc，这里可以写zookeeper和netty连接传输数据的逻辑
				Object result = method.invoke(classValue.newInstance(), args);
				return result;
			}
			
		});
    }
	
	@Test
	public void helloTest1() {
		IService service=(IService) new ServiceTest().getService(IService.class);
		System.out.println(service.hello("mjo"));
	}

}

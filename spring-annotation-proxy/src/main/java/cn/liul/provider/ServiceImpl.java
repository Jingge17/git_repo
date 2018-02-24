package cn.liul.provider;

@RpcService(value1=IService.class,value2="abc")
public class ServiceImpl implements IService {

	public String hello(String name) {
		return "hello!"+name;
	}

}

个人认为spring shiro：

1.此框架用于效验用户身份和页面访问权限认证

2.此框架有配置servlet拦截器，根据用户和用户访问权限拦截用户访问的页面  

3.
  ①控制层调用SecurityUtils.getSubject().login(token)会执行realm的doGetAuthenticationInfo方法，可以结合userservice查询用户信息在内存中生成用户token，
之后验证登录用户与内存token是否匹配用以验证用户登录 （生成token用来过拦截器）
  ②控制层调用SecurityUtils.getSubject().isAuthenticated()会执行realm的doGetAuthorizationInfo方法，可以结合权限表生成带有用户权限的用户token，
之后验证用户访问权限 跳转不同页面


 

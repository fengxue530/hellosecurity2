使用springboot 构建工程一般使用spring-security作为安全框架，下面是使用spring-security验证登陆的基本操作

##初步使用
    网上资料很多，可以自己搜索尝试。例如：https://www.cnblogs.com/lenve/p/11242055.html
    
##实际项目中使用 spring-security 验证登陆权限
###1. 编写security配置类
       a.继承 WebSecurityConfigurerAdapter 创建我们自己的配置类 HelloSecurityConfiguration。 使用注解 @EnableWebSecurity,这个
       注解有2个作用： 
       （1: 加载了WebSecurityConfiguration配置类, 配置安全认证策略。
       （2: 加载了AuthenticationConfiguration, 配置了认证信息
       详细讲解见 https://blog.csdn.net/weixin_42849689/article/details/89953107
       b.编写configure()方法,对应请求和过滤器
       c.注入 AuthenticationManager 需要使用@bean 注解
       
###2.编写登陆过滤器 RestLoginProcessingFilter 
     继承AbstractAuthenticationProcessingFilter，实现attemptAuthentication方法，此方法是用来验证登陆的关键方法。debug可以跟
     踪到ProviderManager 的authenticate() 方法，这个方法会遍历所有 providers,所以此处我们需要实现自己的 RestAuthenticationProvider。
    
##3.编写自己的 RestAuthenticationProvider   
     RestAuthenticationProvider 中从数据库中校验用户是否存在和登陆信息是否正确，并返回校验结果。校验结果使用UsernamePasswordAuthenticationToken封装
     
##4.HelloSecurityConfiguration 中自定义过滤器成功和失败输出 
     创建 MyAuthenticationSuccessHandler继承 SavedRequestAwareAuthenticationSuccessHandler，可以在onAuthenticationSuccess()方法中 
     实现返回json 或者返回页面；创建MyAuthenticationFailureHandler需要继承SimpleUrlAuthenticationFailureHandler
     
##5.测试 
    使用postman 发送post请求  url: http://localhost:8080/api/auth/login   参数：{"username":"张三",	"password":1234}  
    
    完整代码：  
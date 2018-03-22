#基于spring-boot 项目的dubbo的自动配置项目。

#支持非web的方式启动，服务提供方无需引入spring-boot-starter-web也可以启动 

#兼容2.5.3  以及dobbox-2.8.4

#使用说明：
 * 服务提供房/消费者pom.xml 添加该项目 dependency
 
     `     <dependency>	
                <groupId>com.zcckj.starter</groupId>
                <artifactId>zcckj-spring-boot-starter-dubbo</artifactId>
                <version>1.0.0-SNAPSHOT</version>
             </dependency>

    `
 * 添加`@EnableDubboConfiguration`注解
 
 * 在spring.dubbo配置下配置各项dubbo配置
 
 * 服务提供者的接口实现类/消费者的接口引用的 `@Service`/`@Reference`(group)该group值取值yml或者properties文件的key值（可以动态取值了）
 
 * 1.1.RELEASE 更新
    修改兼容性
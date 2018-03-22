package com.zcckj.starter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 李朝衡 on 2017/5/17.
 *  #service #reference 注解扫描配置
 */
@Configuration
public class DubboAnnotationAutoConfiguration {

    @Bean
    public static DubboAnnotationFactory annotationBean(@Value("${spring.dubbo.application.scan-package}")String scanPackage
                                                        ){
        DubboAnnotationFactory annotationFactory = new DubboAnnotationFactory();
        annotationFactory.setPackage(scanPackage);
        return annotationFactory;
    }
}

package com.zcckj.starter.annotation;

import com.zcckj.starter.config.DubboAnnotationAutoConfiguration;
import com.zcckj.starter.config.DubboAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * Created by 李朝衡 on 2017/5/16.
 *  启动注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ImportAutoConfiguration(value = {DubboAutoConfiguration.class, DubboAnnotationAutoConfiguration.class})
@Configuration
public @interface EnableDubboConfiguration {

}

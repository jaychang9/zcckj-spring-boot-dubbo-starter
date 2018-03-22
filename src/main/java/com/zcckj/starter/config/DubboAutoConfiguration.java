package com.zcckj.starter.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.Exporter;
import com.zcckj.starter.annotation.EnableDubboConfiguration;
import com.zcckj.starter.endpoint.DubboEndpoint;
import com.zcckj.starter.health.DubboHealthIndicator;
import com.zcckj.starter.metrics.DubboMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by 李朝衡 on 2017/5/16.
 * dubbo基本配置
 */
@Configuration
@ConditionalOnClass(Exporter.class)
@EnableConfigurationProperties(value = {DubboApplicationProperties.class,
        DubboMonitorProperties.class,DubboProtocolProperties.class,
        DubboRegistryProperties.class})
@ConditionalOnBean(annotation = EnableDubboConfiguration.class)
public class DubboAutoConfiguration {
    @Autowired
    private DubboMonitorProperties dubboMonitorProperties;
    @Autowired
    private DubboApplicationProperties dubboApplicationProperties;

    @Autowired
    private DubboProtocolProperties dubboProtocolProperties;

    @Autowired
    private DubboRegistryProperties dubboRegistryProperties;

    @Bean
    @ConditionalOnMissingBean
    public ApplicationConfig applicationConfig( ) {
        ApplicationConfig config = new ApplicationConfig();
        config.setName(dubboApplicationProperties.getName());
        config.setOwner(dubboApplicationProperties.getOwner());
        config.setVersion(dubboApplicationProperties.getVersion());
        config.setRegistry(registryConfig());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig  = new RegistryConfig();
        registryConfig.setAddress(dubboRegistryProperties.getAddress());
        registryConfig.setCheck(dubboRegistryProperties.getCheck());
        registryConfig.setGroup(dubboRegistryProperties.getGroup());
        registryConfig.setPort(dubboRegistryProperties.getPort());
        registryConfig.setTimeout(dubboRegistryProperties.getTimeout());
        registryConfig.setVersion(dubboRegistryProperties.getVersion());
        registryConfig.setProtocol(dubboRegistryProperties.getProtocol());
        return registryConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setCharset(dubboProtocolProperties.getCharset());
        protocolConfig.setName(dubboProtocolProperties.getName());
        protocolConfig.setPort(dubboProtocolProperties.getPort());
        protocolConfig.setCodec(dubboProtocolProperties.getCodec());
        protocolConfig.setSerialization(dubboProtocolProperties.getSerialization());

        return protocolConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "spring.dubbo.registry.enabled")
    public MonitorConfig monitorConfig() {
        MonitorConfig config = new MonitorConfig();
        config.setAddress(dubboMonitorProperties.getAddress());
        config.setGroup(dubboMonitorProperties.getGroup());
        config.setVersion(dubboMonitorProperties.getVersion());
        return config;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(prefix = "endpoints.dubbo", ignoreUnknownFields = false)
    public DubboEndpoint dubboEndpoint() {
        return new DubboEndpoint();
    }
    @Bean
    public DubboMetrics dubboConsumerMetrics() {
        return new DubboMetrics();
    }
    @Bean
    @ConditionalOnMissingBean
    public DubboHealthIndicator dubboHealthIndicator() {
        return new DubboHealthIndicator();
    }

}

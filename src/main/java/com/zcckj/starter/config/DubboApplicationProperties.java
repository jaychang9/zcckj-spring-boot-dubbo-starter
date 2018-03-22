package com.zcckj.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 李朝衡 on 2017/5/16.
 */
@ConfigurationProperties(prefix = "spring.dubbo.application")
//@Getter
//@Setter
public class DubboApplicationProperties {
    private String scanPackage;
    /**
     * 应用名称
     */
    private String name;

    /**
     * 模块版本
     */
    private String version;

    /**
     * 应用负责人
     */
    private String owner;

    /**
     * 组织名(BU或部门)
     */
    private String organization;

    /**
     * 分层
     */
    private String architecture;

    /**
     * 环境，如：dev/test/run
     */
    private String environment;

    /**
     * Java代码编译器
     */
    private String compiler;

    /**
     * 日志输出方式
     */
    private String logger;

    public String getScanPackage() {
        return scanPackage;
    }

    public void setScanPackage(String scanPackage) {
        this.scanPackage = scanPackage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getCompiler() {
        return compiler;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }
}

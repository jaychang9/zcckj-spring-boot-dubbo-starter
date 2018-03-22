package com.zcckj.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 李朝衡 on 2017/5/16.
 */
@ConfigurationProperties(prefix = "spring.dubbo.registry")
//@Getter
//@Setter
public class DubboRegistryProperties {

    /**
     * 注册中心地址
     */
    private String address = "zookeeper://127.0.0.1:2181";

    /**
     * 注册中心登录用户名
     */
    private String username;

    /**
     * 注册中心登录密码
     */
    private String password;

    /**
     * 注册中心缺省端口
     */
    private Integer port;

    /**
     * 注册中心协议
     */
    private String protocol = "zookeeper";
    /**
     * 服务组
     */
    private String group = "dubbo";
    /**
     * 版本
     */
    private String version;

    /**
     * 注册中心请求超时时间(毫秒)
     */
    private Integer timeout;

    /**
     * 注册中心会话超时时间(毫秒)
     */
    private Integer session;

    /**
     * 停止时等候完成通知时间
     */
    private Integer wait;

    /**
     * 启动时检查注册中心是否存在
     */
    private Boolean check;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Integer getSession() {
        return session;
    }

    public void setSession(Integer session) {
        this.session = session;
    }

    public Integer getWait() {
        return wait;
    }

    public void setWait(Integer wait) {
        this.wait = wait;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }
}

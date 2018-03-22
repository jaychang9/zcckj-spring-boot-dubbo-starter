package com.zcckj.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 李朝衡 on 2017/5/16.
 */
@ConfigurationProperties(prefix = "spring.dubbo.monitor")
//@Getter
//@Setter
public class DubboMonitorProperties {
    /**
     * 协议
     */
    private String protocol;
    /**
     * 地址
     */
    private String address;
    /**
     * group
     */
    private String group;
    /**
     * version
     */
    private String version;
    /**
     * 是否启用监控
     */
    private boolean enabled;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

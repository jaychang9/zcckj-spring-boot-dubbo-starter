package com.zcckj.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by 李朝衡 on 2017/5/16.
 */
@ConfigurationProperties(prefix = "spring.dubbo.protocol")

public class DubboProtocolProperties {
    /**
     * 服务协议
     */
    private String name;

    /**
     * 服务IP地址(多网卡时使用)
     */
    private String host;

    /**
     * 服务端口
     */
    private Integer port = -1;


    /**
     * 协议编码
     */
    private String codec;

    /**
     * 序列化方式
     */
    private String serialization;

    /**
     * 字符集
     */
    private String charset;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getCodec() {
        return codec;
    }

    public void setCodec(String codec) {
        this.codec = codec;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}

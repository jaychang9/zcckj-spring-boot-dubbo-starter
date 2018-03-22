package com.zcckj.starter.endpoint;

import java.util.List;

/**
 * Created by 李朝衡 on 2017/5/16.
 */

public class ConsumerBean {
    private String group;
    private String version;
    private String interfaceName;
    private List<String> methodNames;

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

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<String> getMethodNames() {
        return methodNames;
    }

    public void setMethodNames(List<String> methodNames) {
        this.methodNames = methodNames;
    }
}

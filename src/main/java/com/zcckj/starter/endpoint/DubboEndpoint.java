package com.zcckj.starter.endpoint;

import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.zcckj.starter.config.DubboApplicationProperties;
import com.zcckj.starter.config.DubboProtocolProperties;
import com.zcckj.starter.config.DubboRegistryProperties;
import com.zcckj.starter.domain.ClassIdBean;
import com.zcckj.starter.listener.ConsumerSubscribeListener;
import com.zcckj.starter.listener.ProviderExportListener;
import com.zcckj.starter.listener.StaticsFilterHelper;
import com.zcckj.starter.metrics.DubboMetrics;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by 李朝衡 on 2017/5/17.
 */
public class DubboEndpoint extends AbstractEndpoint<Map<String, Object>> implements ApplicationContextAware {
    @Autowired
    private DubboApplicationProperties dubboApplicationProperties;

    @Autowired
    private DubboRegistryProperties dubboRegistryProperties;
    @Autowired
    private DubboProtocolProperties dubboProtocolProperties;
    @Autowired
    private DubboMetrics dubboMetrics;
    private ApplicationContext context;
    @Override
    public Map<String, Object> invoke() {
        List<ProviderBean> publishedInterfaceList = new ArrayList<>();
        List<ConsumerBean> subscribedInterfaceList = new ArrayList<>();
        AnnotationBean annotationBean =  context.getBean(AnnotationBean.class);
        Field serviceConfigsField = ReflectionUtils.findField(AnnotationBean.class, "serviceConfigs");
        ReflectionUtils.makeAccessible(serviceConfigsField);
        Object services = ReflectionUtils.getField(serviceConfigsField, annotationBean);
        if (services instanceof Set) {
            final Set<ServiceBean<?>> serviceConfigs = (Set<ServiceBean<?>>) services;
            for (ServiceBean config : serviceConfigs) {
                ProviderBean providerBean = new ProviderBean();
                providerBean.setTarget(config.getStub());
                providerBean.setServiceInterface(config.getInterface());
                providerBean.setServiceVersion(config.getVersion());
                providerBean.setClientTimeout(config.getTimeout());
                providerBean.setMethodNames(config.getMethods());
                publishedInterfaceList.add(providerBean);
            }
        }
        Field referenceConfigsField = ReflectionUtils.findField(AnnotationBean.class, "referenceConfigs");
        ReflectionUtils.makeAccessible(referenceConfigsField);
        Object references = ReflectionUtils.getField(referenceConfigsField, annotationBean);
        if (references instanceof ConcurrentMap) {
            final ConcurrentMap<String, ReferenceBean<?>> referenceConfigs = (ConcurrentMap<String, ReferenceBean<?>>) references;
            for (Map.Entry<String, ReferenceBean<?>> reference : referenceConfigs.entrySet()) {
                ReferenceBean referenceBean = reference.getValue();
                ConsumerBean consumerBean = new ConsumerBean();
                consumerBean.setGroup(referenceBean.getGroup());
                consumerBean.setInterfaceName(referenceBean.getInterface());
                consumerBean.setMethodNames(referenceBean.getMethods());
                consumerBean.setVersion(referenceBean.getVersion());
                subscribedInterfaceList.add(consumerBean);
            }
        }



        Map<String, Object> result = new HashMap<String, Object>();
//        result.put("endpoint", this.buildEndpoint());
        result.put("metrics", this.getMetrics());
//        result.put("config", this.getConfig());
        result.put("runtime", this.getRuntime());
        result.put("provider", publishedInterfaceList);
        result.put("consumer", subscribedInterfaceList);
        return result;
    }



    public DubboEndpoint() {
        super("dubbo");
    }

    public String getName() {
        return "dubbo";
    }

    public String getVersion() {
        return "1.0.0";
    }




    public Map<String, Object> getRuntime() {
        Map<String, Object> runtimeMap = new HashMap<String, Object>();

        runtimeMap.put("application", dubboApplicationProperties);
        runtimeMap.put("registry", dubboRegistryProperties);
        runtimeMap.put("protocol", dubboProtocolProperties);


        // published services
        Map<ClassIdBean, Map<String, Long>> publishedInterfaceList =
                new HashMap<ClassIdBean, Map<String, Long>>();
        Set<ClassIdBean> publishedInterfaceSet = ProviderExportListener.EXPORTEDINTERFACES_SET;
        for (ClassIdBean classIdBean : publishedInterfaceSet) {
            Class<?> clazz = classIdBean.getClazz();
            String interfaceClassCanonicalName = clazz.getCanonicalName();
            if (!interfaceClassCanonicalName.equals("void")) {
                Map<String, Long> methodNames = new HashMap<String, Long>();
                for (Method method : clazz.getMethods()) {
                    methodNames.put(method.getName(),
                            StaticsFilterHelper.getValue(classIdBean, method.getName()));
                }
                publishedInterfaceList.put(classIdBean, methodNames);
            }
        }
        if (!publishedInterfaceList.isEmpty()) {
            runtimeMap.put("publishedInterfaces", publishedInterfaceList);
        }

        // subscribed services
        Set<ClassIdBean> subscribedInterfaceSet = ConsumerSubscribeListener.SUBSCRIBEDINTERFACES_SET;
        Map<ClassIdBean, Map<String, Long>> subscribedInterfaceList =
                new HashMap<ClassIdBean, Map<String, Long>>();
        for (ClassIdBean classIdBean : subscribedInterfaceSet) {
            Map<String, Long> methodNames = new HashMap<String, Long>();
            Class<?> clazz = classIdBean.getClazz();
            for (Method method : clazz.getMethods()) {
                methodNames.put(method.getName(),
                        StaticsFilterHelper.getValue(classIdBean, method.getName()));
            }
            subscribedInterfaceList.put(classIdBean, methodNames);
        }
        if (!subscribedInterfaceList.isEmpty()) {
            runtimeMap.put("subscribedInterfaces", subscribedInterfaceList);
        }

        // consumer connections
        runtimeMap.put("connections", ConsumerSubscribeListener.CONNECTION_MAP);
        return runtimeMap;
    }

    public Map<String, Object> getMetrics() {
        Map<String, Object> metricsMap = new HashMap<String, Object>();
        Collection<Metric<?>> metrics = this.dubboMetrics.metrics();
        for (Metric<?> metric : metrics) {
            metricsMap.put(metric.getName(), metric.toString());
        }
        return metricsMap;
    }


    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

}

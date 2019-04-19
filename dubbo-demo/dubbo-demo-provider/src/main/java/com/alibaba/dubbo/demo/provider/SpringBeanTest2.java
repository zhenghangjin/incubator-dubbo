package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpringBeanTest2 implements FactoryBean,ApplicationContextAware, InitializingBean{

    protected static final Logger logger = LoggerFactory.getLogger(SpringBeanTest2.class);

    private static ApplicationContext applicationContext;

    private DemoServiceImpl demoService;
    private DemoService2Impl demoService2;

    public SpringBeanTest2() {
        logger.error("test-- 2SpringBeanTest");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.error("test-- 2ApplicationContextAware setApplicationContext");
        SpringBeanTest2.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.error("test-- 2InitializingBean afterPropertiesSet");
    }

    public void setDemoService(DemoServiceImpl demoService) {
        logger.error("test-- 2set setDemoService");
        this.demoService = demoService;
    }

    public void setDemoService2(DemoService2Impl demoService2) {
        logger.error("test-- 2set setDemoService2");
        this.demoService2 = demoService2;
    }

    @Override
    public Object getObject() throws Exception {
        logger.error("test-- 2getObject ");
        List a = new ArrayList();
        a.add("张三");
        a.add("lisi");
        return a;
    }

    @Override
    public Class<?> getObjectType() {
        logger.error("test-- 2getObjectType ");
        return List.class;
    }

    @Override
    public boolean isSingleton() {
        logger.error("test-- 2isSingleton ");
        return true;
    }
}

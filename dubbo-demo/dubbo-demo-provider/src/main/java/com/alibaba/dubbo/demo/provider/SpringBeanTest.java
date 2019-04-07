package com.alibaba.dubbo.demo.provider;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Service
public class SpringBeanTest implements FactoryBean,ApplicationContextAware, InitializingBean{

    protected static final Logger logger = LoggerFactory.getLogger(SpringBeanTest.class);

    private static ApplicationContext applicationContext;

    private DemoServiceImpl demoService;
    private DemoService2Impl demoService2;

    public SpringBeanTest() {
        logger.error("test-- SpringBeanTest");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.error("test-- ApplicationContextAware setApplicationContext");
        SpringBeanTest.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.error("test-- InitializingBean afterPropertiesSet");
    }

    public void setDemoService(DemoServiceImpl demoService) {
        logger.error("test-- set setDemoService");
        this.demoService = demoService;
    }

    public void setDemoService2(DemoService2Impl demoService2) {
        logger.error("test-- set setDemoService2");
        this.demoService2 = demoService2;
    }

    @Override
    public Object getObject() throws Exception {
        logger.error("test-- getObject ");
        return "我是";
    }

    @Override
    public Class<?> getObjectType() {
        logger.error("test-- getObjectType ");
        return String.class;
    }

    @Override
    public boolean isSingleton() {
        logger.error("test-- isSingleton ");
        return true;
    }
}

package com.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *  @author wangzi
 */
public class ApplicationUtil implements ApplicationContextAware{
    private static ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationUtil.applicationContext = applicationContext;
    }
    public static Object getBean(String name){
        return ApplicationUtil.applicationContext.getBean(name);
    }
}

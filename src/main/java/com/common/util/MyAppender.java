package com.common.util;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Priority;
/**
 * @author wangzi
 * @date 17/10/19 下午11:25.
 */
public class MyAppender extends DailyRollingFileAppender {  
    
    @Override  
    public boolean isAsSevereAsThreshold(Priority priority) {    
          //只判断是否相等，而不判断优先级     
        return this.getThreshold().equals(priority);    
    }    
}  


package com.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author wangzi
 * @Date 2020-06-19 10:44
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    private static final Logger Log = LoggerFactory.getLogger(LogAspect.class);
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    @Pointcut(value = "execution(* com.hlj.service.Impl.ReadServiceImpl.test*(..))")
    public void pointCut() {
    }
//    /**
//     * 在方法执行之前执行
//     *
//     * @param joinPoint
//     */
//    @Before(value = "pointCut()")
//    public void beforeLog(JoinPoint joinPoint) {
//        System.out.println("进入LogAop的beforeLogger");
//        startTime.set(System.currentTimeMillis());
//    }

    /**
     * 在进入类之前执行，然后返回pjp.proceed()之前执行before，再执行方法体，在到after
     *
     * @param
     */
    @Around(value = "pointCut()")
    public Object aroundLog(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();

        try {

            String reqStr = JSONObject.toJSONStringWithDateFormat( joinPoint.getArgs()[0] , DATE_FORMAT,
                    SerializerFeature.PrettyFormat ,SerializerFeature.WriteMapNullValue );
            log.info("{}[]{}[]入参:{}" , targetClass.getName() , method.getName() , reqStr );
            Object result = joinPoint.proceed();

            String respStr = JSONObject.toJSONStringWithDateFormat(result , DATE_FORMAT ,
                    SerializerFeature.PrettyFormat ,SerializerFeature.WriteMapNullValue);
            log.info("{}[]{}[]出参:{}" , targetClass.getName() , method.getName() , respStr );

            return result;
        } catch ( Exception e ) {
            log.error("{}[]{}[]失败 error:{}" , targetClass.getName() , method.getName() ,  e.getMessage() );
        }
        return null;
    }

//    /**
//     * 在方法执行返回之后执行
//     */
//    @After(value = "pointCut()")
//    public void afterLog() {
//        System.out.println("进入LogAop的afterLogger");
//        long start = startTime.get();
//        System.out.println("方法体执行耗时：" + (System.currentTimeMillis() - start) + "ms");
//        startTime.remove();
//    }

//    /**
//     * 在返回之后执行
//     * @param o
//     */
//    @AfterReturning(value = "pointCut()",returning = "o")
//    public void afterRunningLog(Object o){
//        System.out.println("进入LogAop的afterRunningLog");
//        System.out.println(o.getClass());
//    }

//    /**
//     * 在产生异常时执行
//     */
//    @AfterThrowing(value = "pointCut()")
//    public void afterThrowingLog(){
//        System.out.println("进入LogAop的afterThrowingLog");
//    }

}

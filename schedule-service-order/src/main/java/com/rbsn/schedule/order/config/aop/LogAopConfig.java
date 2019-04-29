package com.rbsn.schedule.order.config.aop;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@Component
@Aspect
public class LogAopConfig {
	/** 日志.*/
	private static final Log LOGGER = LogFactory.getLog(LogAopConfig.class);
	/** 日志格式.*/
	private static final String LOG_STRING_FORMAT1 = ">>>>>>>>>>>>>>> Class: %1$s, Method: %2$s,Args: %3$s. start. >>>>>>>>>>>>>>>";
	/** 日志格式.*/
	//private static final String LOG_STRING_FORMAT2 = "<<<<<<<<<<<<<<< Class: %1$s, Method: %2$s end. <<<<<<<<<<<<<<<";
	/** 日志格式.*/
	private static final String LOG_STRING_FORMAT3 = "!!!!!!!!!!!!!!! Exception: %1$s, Method: %2$s Message: %3$s. !!!!!!!!!!!!!!!";
	/** 日志格式.*/
	private static final String LOG_STRING_FORMAT4 = "<<<<<<<<<<<<<<< Class: %1$s, Method: %2$s,Result:  %3$s end. <<<<<<<<<<<<<<<";
	//匹配com.zkn.learnspringboot.web.controller包及其子包下的所有类的所有方法  
    @Pointcut("execution(* com.ld.yunlanwan.web..*.*(..)) or execution(* com.ld.yunlanwan.service..*.*(..))")
    public void execute(){  
  
    }  
  
    /** 
     * 前置通知，方法调用前被调用 
     * @param joinPoint 
     */  
    @Before("execute()")
    public void doBeforeAdvice(JoinPoint joinPoint){
        //代理的目标对象  
        joinPoint.getTarget();  
        //获取RequestAttributes  
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息  
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        //如果要获取Session信息的话，可以这样写：  
        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);  
        Enumeration<String> enumeration = request.getParameterNames();  
        Map<String,String> parameterMap = Maps.newHashMap();  
        while (enumeration.hasMoreElements()){  
            String parameter = enumeration.nextElement();  
            parameterMap.put(parameter,request.getParameter(parameter));  
        }  
        ObjectMapper objMapper=new ObjectMapper();
        String str=null;
		try {
			str = objMapper.writeValueAsString(parameterMap);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}  
		LOGGER.info(String.format(LOG_STRING_FORMAT1,
				joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName(),str));
    }  
  
  
    /** 
     * 后置异常通知 
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法； 
     *  throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行， 
     *      对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。 
     * @param joinPoint 
     * @param exception 
     */  
    @AfterThrowing(value = "execute()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint, Throwable exception){
    	exception.printStackTrace();
        //目标方法名：  
        LOGGER.error(String.format(LOG_STRING_FORMAT3,
				joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName(),exception.getMessage()));
    }  
  
    /** 
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法） 
     * @param joinPoint 
     */  
   /* @After("execute()")  
    public void doAfterAdvice(JoinPoint joinPoint){  
    	LOGGER.debug(String.format(LOG_STRING_FORMAT2,
				joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName()));
    }  */
    
    @AfterReturning(
    		value = "execute()",returning="result")
	 public void afterReturning(JoinPoint joinPoint , Object result) {
    	LOGGER.info(String.format(LOG_STRING_FORMAT4,
				joinPoint.getTarget().getClass().getName(),joinPoint.getSignature().getName(),JSONObject.toJSONString(result)));
	 
	 }
  
}

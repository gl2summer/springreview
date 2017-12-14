package com.hao.controller;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProtocolParseAspect {

	@Pointcut("execution(public * com.hao.controller.ProtocolParseImpl.*(..))")
	public void JoinPointExpression() {}

//	@Before("JoinPointExpression()")
//	public void beforeMethod(JoinPoint joinPoint) {
//		System.out.println("<Before>The method (" +joinPoint.getSignature().getName() +") begins with: " +Arrays.asList(joinPoint.getArgs()));
//	}
//	
//	@After("JoinPointExpression()")
//	public void afterMethod(JoinPoint joinPoint) {
//		System.out.println("<After>The method(" +joinPoint.getSignature().getName() +") end");
//	}
//	
//	@AfterReturning(value="JoinPointExpression()", returning="result")
//	public void afterReturnMethod(JoinPoint joinPoint, Object result) {
//		System.out.println("<AfterReturning>The method(" +joinPoint.getSignature().getName() +") end with: " +result);
//	}
//	
//	@AfterThrowing(value="JoinPointExpression()", throwing="ex")
//	public void afterThrowing(JoinPoint joinPoint, Exception ex) {
//		System.out.println("<AfterThrowing>The method(" +joinPoint.getSignature().getName() +") occurs expetions: " +ex);
//	}

	@Around("JoinPointExpression()")
	public Object aroundMethod(ProceedingJoinPoint pjp) {
		Object result = null;
		String methodName = pjp.getSignature().getName();
		try {
			System.out.println("<Around>The method(" +methodName +") begins with: " +Arrays.asList(pjp.getArgs()));
			result = pjp.proceed();
			System.out.println("<Around>The method(" +methodName +") end with: " +result);
		} catch (Throwable e) {
			System.out.println("<Around>The method(" +methodName +") occurs expetions: " +e);
			throw new RuntimeException(e);
		}
		System.out.println("<Around>The method(" +methodName +") end");
		return result;
	}
	
	@Before("execution(public * com.hao.controller.ProtocolParseImpl.protCombine(..))")
	public void beforeMethod(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();
		if((args[0] != null)&&(((Byte)args[0]) > '9')){
			throw new IllegalArgumentException("value is illegal!");
		}
	}
}

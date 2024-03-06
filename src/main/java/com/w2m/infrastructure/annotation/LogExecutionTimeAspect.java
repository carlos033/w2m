package com.w2m.infrastructure.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LogExecutionTimeAspect {
	private static final String MESSAGE = "{} executed in {}ms";

	@Around("@annotation(LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		final long start = System.currentTimeMillis();

		final Object proceed = joinPoint.proceed();

		final long executionTime = System.currentTimeMillis() - start;

		log.info(MESSAGE, joinPoint.getSignature(), executionTime);

		return proceed;
	}
}

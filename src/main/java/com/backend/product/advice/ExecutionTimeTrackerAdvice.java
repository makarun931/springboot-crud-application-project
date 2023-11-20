package com.backend.product.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

@Aspect
@Component
public class ExecutionTimeTrackerAdvice {
    Logger logger = LoggerFactory.getLogger(ExecutionTimeTrackerAdvice.class);

    @Around("@annotation(com.backend.product.advice.TrackExecutionTime)")
    public Object trackTime(ProceedingJoinPoint pjp) throws Throwable {
        long stratTime = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long endTime = System.currentTimeMillis();
        logger.info("Method name" + pjp.getSignature() + " time taken to execute : " + (endTime - stratTime));
        return obj;
    }

}

package com.library.e_library.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Aspect
@Configuration
@Slf4j
public class LoggingAop {

    @AfterThrowing(pointcut = "execution(* com.library.e_library.*.*.*(..))", throwing = "ex")
    public void logException(JoinPoint jp, Exception ex)
    {
        log.info("{} Exception occurred in method {} at {}", ex.getMessage(), jp.getSignature().getName(), LocalDateTime.now());
    }

    @AfterReturning(pointcut = "execution(* com.library.e_library.*.*.*(..))")
    public void logMethodSuccess(JoinPoint jp)
    {
        log.info("Method execution successful  {}", jp.getSignature().getName());
    }



}

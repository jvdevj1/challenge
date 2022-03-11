package com.baluwo.challenge.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Before("execution(* com.baluwo.challenge.domain.service.impl.*.*(..)))")
    public void beforeLogService(JoinPoint joinPoint) {
        log.info("{}: Start - Input arguments: {}", getClassAndMethod(joinPoint), getArgs(joinPoint));
    }

    @AfterReturning(pointcut = "execution(* com.baluwo.challenge.domain.service.impl.*.*(..)))")
    public void afterSuccessLogService(JoinPoint joinPoint) {
        log.info("{}: Service responds correctly - Input arguments: {}", getClassAndMethod(joinPoint), getArgs(joinPoint));
    }

    @AfterThrowing(pointcut = "execution(* com.baluwo.challenge.domain.service.impl.*.*(..)))", throwing = "e")
    public void afterFailureLogService(JoinPoint joinPoint, Exception e) {
        log.error("{}: Error in service - Input arguments: {} - {}", getClassAndMethod(joinPoint), getArgs(joinPoint), e.getMessage(), e);
    }

    @Before("execution(* com.baluwo.challenge.app.rest.*.*(..)))")
    public void beforeLogController(JoinPoint joinPoint) {
        log.info("{}: Start - Input arguments: {}", getClassAndMethod(joinPoint), getArgs(joinPoint));
    }

    @AfterReturning(pointcut = "execution(* com.baluwo.challenge.app.rest.*.*(..)))")
    public void afterSuccessLogController(JoinPoint joinPoint) {
        log.info("{}: Controller responds correctly - Input arguments: {}", getClassAndMethod(joinPoint), getArgs(joinPoint));
    }

    private String getClassAndMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        return String.format("%s.%s", className, methodName);
    }

    private String getArgs(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        return IntStream.range(0, args.length)
                .mapToObj(i -> parameterNames[i] + ": " + args[i])
                .collect(Collectors.joining(", "));
    }
}

package cz.fel.cvut.translationapp.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class RequestLogAspect {

	// @Before("execution(* cz.fel.cvut.translationapp.service.repository.*.*())")
	// public void getAllAdvice(){
	// System.out.println("Service method getter called");
	// }
//	@Around("execution(public String getName())")
//	public void getNameAdvice() {
//		System.out.println("Executing Advice on getName()");
//	}

	// @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	// public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws
	// Throwable {
	// HttpServletRequest request = ((ServletRequestAttributes)
	// RequestContextHolder.currentRequestAttributes())
	// .getRequest();
	//
	// Object value;
	//
	// try {
	// value = proceedingJoinPoint.proceed();
	// } catch (Throwable throwable) {
	// throw throwable;
	// } finally {
	// log.info("{} {} from {}", request.getMethod(), request.getRequestURI(),
	// request.getRemoteAddr(),
	// request.getHeader("user-id"));
	// }
	//
	// return value;
	// }
}
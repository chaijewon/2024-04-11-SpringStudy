package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // 공통모듈 => 메모리 할당은 할 수 없다 => 자동 호출 (Callback)
// OOP는 자동 호출이 없다 => 무조건 메소드 호출만 가능 
@Component
public class FoodAOP {
  @Around("execution(* com.sist.web.*Controller.*(..))")
  public Object log(ProceedingJoinPoint jp)
  {
	  Object obj=null;
	  try
	  {
		  long start=System.currentTimeMillis();
		  obj=jp.proceed(); // 사용자가 요청한 메소드 호출
		  long end=System.currentTimeMillis();
		  System.out.println("요청한 메소드:"+jp.getSignature().getName());
		  System.out.println("소요 시간:"+(end-start));
	  }catch(Throwable ex) {}
	  return obj;
  }
}

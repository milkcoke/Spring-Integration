package falcon.springpractice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

// AOP!
@Aspect
public class TimeTraceAop {

    // spring practice 패키지 하위에 모두 적용
    // Spring Config 에 등록된 것과 순환참조 일어나지 않도록 예외 처리
    @Around("execution(* falcon.springpractice..*(..)) && !target(falcon.springpractice.SpringConfig)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        // joinPoint 에서 메소드 호출시마다 인터셉트를 걸어서 확인하게함.
        System.out.println("START: " + joinPoint.toString());

        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint + " " + timeMs + "ms");
        }

    }
}

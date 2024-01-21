package io.junrock.GAZA.aop;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class LogAop {
    private final ThreadLocalTrace threadLocalTrace;

    @Around("@annotation(Trace)")
    public Object logTrace(ProceedingJoinPoint pjp) throws Throwable{
        TraceStatus traceStatus=null;
        try{
            traceStatus=threadLocalTrace.start(pjp.getSignature().toShortString());
            Object result=pjp.proceed();
            threadLocalTrace.end(traceStatus);
            return result;
        }catch (Throwable e){
            e.printStackTrace();
            threadLocalTrace.exception(traceStatus,e);
            throw e;
        }
    }
}

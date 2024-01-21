package io.junrock.GAZA.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@Slf4j
public class ThreadLocalTrace {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";
    private static final String APPEND="|";
    private static final String SPACE_APPEND="|   ";
    private ThreadLocal<TraceDepth> threadLocal=new ThreadLocal<>();

    public TraceStatus start(String methodName){
        syncTraceDepth();
        TraceDepth traceDepth=threadLocal.get();
        Long startTime=System.currentTimeMillis();log.info("{}{}",
                addSpace(START_PREFIX, traceDepth.getDepth()),
                methodName);
        return new TraceStatus(traceDepth,startTime,methodName);
    }

    private void syncTraceDepth() {
        TraceDepth traceDepth=threadLocal.get();
        if(traceDepth==null)
            threadLocal.set(new TraceDepth());
        else
            threadLocal.set(traceDepth.createTrace());
    }

    public void end(TraceStatus status) {
        complete(status, null);
    }

    public void exception(TraceStatus status, Throwable e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Throwable e) {
        Long stopTime = System.currentTimeMillis();
        long resultTimeMs = stopTime - status.getStartTime();

        if (e == null) {
            log.info("{}{} time={}ms",
                    addSpace(COMPLETE_PREFIX, status.getTraceDepth().getDepth()),
                    status.getMethodName(),
                    resultTimeMs);
        } else {
            log.error("{}{} time={}ms ex={}",
                    addSpace(EX_PREFIX, status.getTraceDepth().getDepth()),
                    status.getMethodName(),
                    resultTimeMs,
                    e.toString());
        }
        releaseTraceId();
    }

    private void releaseTraceId() {
        TraceDepth traceDepth = threadLocal.get();
        if(traceDepth.isFirstLevel()){
            threadLocal.remove();
        }else{
            threadLocal.set(traceDepth.endTrace());
        }
    }

    private static String addSpace(String prefix, int level) {
        return IntStream.range(0, level).mapToObj(i -> (i == level - 1)
                ? APPEND + prefix
                : SPACE_APPEND).collect(Collectors.joining());
    }
}

package io.junrock.GAZA.aop;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TraceStatus {
    private TraceDepth traceDepth;
    private Long startTime;
    private String methodName;
}

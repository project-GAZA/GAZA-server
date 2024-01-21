package io.junrock.GAZA.aop;

import lombok.Getter;
import org.aspectj.weaver.tools.Trace;

@Getter
public class TraceDepth {
    private int depth;
    public TraceDepth(){
        this.depth=0;
    }
    public TraceDepth(int depth) {
        this.depth = depth;
    }

    public TraceDepth createTrace(){
        return new TraceDepth(depth+1);
    }

    public TraceDepth endTrace(){
        return new TraceDepth(depth-1);
    }

    public boolean isFirstLevel() {
        return depth==0;
    }
}

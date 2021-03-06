/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午5:31:50
 */
package tools.invoker;

import tools.invoker.execution.DefaultAsyncExecution;
import tools.invoker.execution.DefaultSyncExecution;

/**
 * 功能描述：
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午5:31:50
 */
public class Invokers {
    /**
     * 创建默认同步执行的执行者
     * @return
     */
    public static Invoker newSyncInvoker(){
        return new Invoker().setExecutionStrategy(new DefaultSyncExecution());
    }

    /**
     * 创建默认异步执行的执行者。此执行情况下，会忽略failsafe和failfast配置，执行所有加入的cmd
     * @return
     */
    public static Invoker newAsyncInvoker(int poolSize){
        return new Invoker().setExecutionStrategy(new DefaultAsyncExecution(poolSize));
    }
}

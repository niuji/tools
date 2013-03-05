/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午5:33:57
 */
package tools.invoker;

import org.junit.Test;

import tools.invoker.command.FailFastCommand;

/**
 * 功能描述：
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午5:33:57
 */
public class InvokerTest {
    @Test
    public void testSyncInvoker(){
        Invokers.newSyncInvoker().addLogic("更新数据", new FailFastCommand<Integer>() {

            @Override
            public Integer execute() throws Exception {
                return 1;
            }

        }).execute();
    }
}

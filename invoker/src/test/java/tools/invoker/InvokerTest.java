/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午5:33:57
 */
package tools.invoker;

import org.junit.Test;

import tools.invoker.command.SimpleCommand;

/**
 * 功能描述：
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午5:33:57
 */
public class InvokerTest {
    @Test
    public void testSyncInvoker(){
        Invokers.newInvoker().addLogic("更新数据", new SimpleCommand<Integer>() {

            @Override
            public Integer execute() throws Exception {
                return 1;
            }

        }).execute();
    }
}

/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午5:33:57
 */
package tools.invoker;

import org.junit.Assert;
import org.junit.Test;
import tools.invoker.command.CommandDescriptor;
import tools.invoker.command.FailFastCommand;
import tools.invoker.command.FailSafeCommand;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

/**
 * 功能描述：
 *
 * @author jiangyixin.stephen
 *         time : 2013-2-25 下午5:33:57
 */
public class InvokerTest {
    @Test
    public void testSyncInvoker() {
        Result result = Invokers.newSyncInvoker().addLogic("更新数据", new FailFastCommand<Integer>() {

            @Override
            public Integer execute() throws Exception {
                return 1;
            }

        }).execute().getResults();
        List<CommandDescriptor> cmds = result.getCompletedCmds();
        Assert.assertThat((Integer) cmds.get(0).getResult(), is(new Integer(1)));
    }

    @Test
    public void testFailFastCommand() {
        Result result = Invokers.newSyncInvoker().addLogic("抛出异常", new FailFastCommand<Integer>() {

            @Override
            public Integer execute() throws Exception {
                throw new Exception("异常");
            }

        }).addLogic("不会执行", new FailFastCommand<Integer>() {

            @Override
            public Integer execute() throws Exception {
                return 1;
            }

        }).execute().getResults();
        List<CommandDescriptor> cmds = result.getCompletedCmds();
        Assert.assertThat(cmds.size(), is(1));
        Assert.assertThat(cmds.get(0).getName(), is("抛出异常"));
    }

    @Test
    public void testFailSafeCommand() {
        Result result = Invokers.newSyncInvoker().addLogic("抛出异常", new FailSafeCommand<Integer>() {

            @Override
            public Integer execute() throws Exception {
                throw new Exception("异常");
            }

        }).addLogic("会被执行", new FailFastCommand<Integer>() {

            @Override
            public Integer execute() throws Exception {
                return 1;
            }

        }).execute().getResults();
        List<CommandDescriptor> cmds = result.getCompletedCmds();
        Assert.assertThat(cmds.size(), is(2));
        Assert.assertThat(cmds.get(1).getName(), is("会被执行"));
    }
}

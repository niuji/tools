/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午3:46:01
 */
package tools.invoker.execution;

import java.util.List;

import tools.invoker.Result;
import tools.invoker.command.CommandDescriptor;

/**
 * 功能描述：
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午3:46:01
 */
public class DefaultSyncExecution extends AbstExecutionStrategy {
    @Override
    public Result execute(List<CommandDescriptor> cmds) {
        Result result = new Result();
        for (CommandDescriptor desc : cmds) {
            execCmd(desc, result);
        }
        return result;
    }
}

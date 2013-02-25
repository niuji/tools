/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午3:38:29
 */
package tools.invoker.execution;

import java.util.List;

import tools.invoker.Result;
import tools.invoker.command.CommandDescriptor;


/**
 * 功能描述：
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午3:38:29
 */
public interface ExecutionStrategy {
    public Result execute(List<CommandDescriptor> cmds);
}

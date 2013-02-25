/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午5:21:13
 */
package tools.invoker.execution;

import tools.invoker.Result;
import tools.invoker.command.Command;
import tools.invoker.command.CommandDescriptor;


/**
 * 功能描述：
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午5:21:13
 */
public abstract class AbstExecutionStrategy implements ExecutionStrategy {

    protected void execCmd(CommandDescriptor desc, Result result){
        Command cmd = desc.getCmd();
        try {
            Object rt = cmd.execute();
            desc.setResult(rt);
            cmd.onReturn(rt);
        } catch (Exception e) {
            desc.setEx(e);
            cmd.onError(e);
        }
    }

}
/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午5:21:13
 */
package tools.invoker.execution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.invoker.command.Command;
import tools.invoker.command.CommandDescriptor;


/**
 * 功能描述：
 *
 * @author jiangyixin.stephen
 *         time : 2013-2-25 下午5:21:13
 */
public abstract class AbstExecutionStrategy implements ExecutionStrategy {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected boolean execCmd(CommandDescriptor desc) {
        Command cmd = desc.getCmd();
        try {
            Object rt = cmd.execute();
            desc.setResult(rt);
            cmd.onReturn(rt);
            return true;
        } catch (Exception e) {
            desc.setEx(e);
            try {
                return cmd.onError(e);
            } catch (Exception ex) {
                logger.warn("execute command[" + desc.getName() + "].onError() failed. ", ex);
                return false;
            }
        }
    }

}
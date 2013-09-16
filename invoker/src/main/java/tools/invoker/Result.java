/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午3:51:35
 */
package tools.invoker;

import tools.invoker.command.CommandDescriptor;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *
 * @author jiangyixin.stephen
 *         time : 2013-2-25 下午3:51:35
 */
public class Result {
    private List<CommandDescriptor> cmds = new ArrayList<CommandDescriptor>();

    public Result(List<CommandDescriptor> cmds) {
        this.cmds = cmds;
    }

    public List<CommandDescriptor> getCompletedCmds() {
        List<CommandDescriptor> rt = new ArrayList<CommandDescriptor>();
        for (CommandDescriptor cmd : cmds) {
            switch (cmd.getStatus()) {
                case SUCCESS:
                case FAIL:
                    rt.add(cmd);
                    break;
            }
        }
        return rt;
    }
}

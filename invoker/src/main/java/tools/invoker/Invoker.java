/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午3:04:59
 */
package tools.invoker;

import java.util.LinkedList;
import java.util.List;

import tools.invoker.command.Command;
import tools.invoker.command.CommandDescriptor;
import tools.invoker.execution.DefaultSyncExecution;
import tools.invoker.execution.ExecutionStrategy;

/**
 * 功能描述：
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午3:04:59
 */
public class Invoker {
    // 依次存放需要执行的逻辑
    private List<CommandDescriptor> cmds = new LinkedList<>();
    
    private ExecutionStrategy executionStrategy = new DefaultSyncExecution();
    
    public Invoker addLogic(String name, Command<?> cmd){
        cmds.add(new CommandDescriptor(name, cmd));
        return this;
    }
    
    /**
     * 执行所有命令逻辑
     * @return
     */
    public Result execute(){
        return executionStrategy.execute(cmds);
    }
}

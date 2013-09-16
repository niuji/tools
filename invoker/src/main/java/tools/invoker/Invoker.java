/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午3:04:59
 */
package tools.invoker;

import tools.invoker.command.Command;
import tools.invoker.command.CommandDescriptor;
import tools.invoker.execution.ExecutionStrategy;

import java.util.LinkedList;
import java.util.List;

/**
 * 功能描述：短时间批量调用工具类
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午3:04:59
 */
public class Invoker {
    // 依次存放需要执行的逻辑
    private List<CommandDescriptor> cmds = new LinkedList<CommandDescriptor>();
    
    private ExecutionStrategy executionStrategy;
    
    public Invoker addLogic(String name, Command<?> cmd){
        cmds.add(new CommandDescriptor(name, cmd));
        return this;
    }
    
    /**
     * 使用executionStrategy执行所有命令逻辑
     * @return
     */
    public Invoker execute(){
        executionStrategy.execute(cmds);
        return this;
    }

    /**
     * 获取执行结果
     * @return
     */
    public Result getResults(){
        executionStrategy.waitForComplete();
        return new Result(cmds);
    }

    public Invoker setExecutionStrategy(ExecutionStrategy executionStrategy) {
        this.executionStrategy = executionStrategy;
        return this;
    }
}
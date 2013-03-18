/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午3:26:34
 */
package tools.invoker.command;

import tools.invoker.command.constants.Status;

/**
 * 功能描述：
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午3:26:34
 */
public class CommandDescriptor {
    private String name;
    private Command<?> cmd;
    private Object result;
    private Exception ex;
    private Status status;

    /**
     * @param cmd
     */
    public CommandDescriptor(String name, Command<?> cmd) {
        super();
        this.name = name;
        this.cmd = cmd;
        status = Status.INIT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Command<?> getCmd() {
        return cmd;
    }

    public void setCmd(Command<?> cmd) {
        this.cmd = cmd;
    }

    public Object getResult() {
        return result;
    }

    /**
     * set执行返回值，并设置状态为success
     * @param result
     */
    public void setResult(Object result) {
        this.result = result;
        this.status = Status.SUCCESS;
    }

    public Exception getEx() {
        return ex;
    }

    /**
     * set异常，并社会自状态为fail
     * @param ex
     */
    public void setEx(Exception ex) {
        this.ex = ex;
        this.status = Status.FAIL;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

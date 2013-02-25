/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午3:14:13
 */
package tools.invoker.command;

/**
 * 功能描述：
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午3:14:13
 */
public interface Command<T> {
    public T execute() throws Exception;
    public void onReturn(T result) throws Exception;
    public void onError(Exception ex);
}

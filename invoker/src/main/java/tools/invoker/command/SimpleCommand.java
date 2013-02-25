/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午5:43:19
 */
package tools.invoker.command;

/**
 * 功能描述：
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午5:43:19
 */
public abstract class SimpleCommand<T> implements Command<T> {
    @Override
    public void onError(Exception ex) {

    }

    @Override
    public void onReturn(T result) throws Exception {

    }
}

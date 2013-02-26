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
public abstract class FailFastCommand<T> implements Command<T> {
    @Override
    public boolean onError(Exception ex) {
        return false;
    }

    @Override
    public void onReturn(T result) throws Exception {

    }
}

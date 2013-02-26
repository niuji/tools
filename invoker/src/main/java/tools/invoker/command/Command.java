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
    /**
     * 执行逻辑，需子类实现
     * @return
     * @throws Exception
     */
    public T execute() throws Exception;

    /**
     * 返回值的处理
     * @param result
     * @throws Exception
     */
    public void onReturn(T result) throws Exception;

    /**
     * 执行异常后的处理
     * @param ex
     * @return true 继续执行下一个command<br>
     *         false 停止执行
     */
    public boolean onError(Exception ex);
}

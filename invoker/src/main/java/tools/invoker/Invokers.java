/*
 * Copyright 2010 sdp.com, Inc. All rights reserved.
 * sdp.com PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * creator : jiangyixin.stephen
 * create time : 2013-2-25 下午5:31:50
 */
package tools.invoker;

/**
 * 功能描述：
 * @author jiangyixin.stephen
 * time : 2013-2-25 下午5:31:50
 */
public class Invokers {
    /**
     * 创建同步执行，failfast的执行者
     * @return
     */
    public static Invoker newInvoker(){
        return new Invoker();
    }
}

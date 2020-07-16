package com.kingc.assessstandardtool.dubbo.filter;


import com.alibaba.dubbo.rpc.RpcException;
import com.seed.dubbo.context.RpcCurrentUser;

/**
 * dubbo上下文
 *
 * @author Administrator
 */
public class DubboContext {

    private static ThreadLocal<RpcCurrentUser> subjectThreadLocal = new ThreadLocal<>();

    protected static <T extends RpcCurrentUser> void setSubjectThreadLocal(T t) {
        if (t == null) {
            throw new RpcException("当前用户不存在");
        }
        subjectThreadLocal.set(t);
    }

    public static RpcCurrentUser getCurrentUser() {
        return subjectThreadLocal.get();
    }

}

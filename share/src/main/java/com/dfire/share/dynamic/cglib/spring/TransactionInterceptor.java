package com.dfire.share.dynamic.cglib.spring;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>Package:com.dfire.share.dynamic.cglib.spring</p>
 * <p>Description: </p>
 * <p>Company: com.lowan</p>
 *
 * @author wjj
 * @date 2019/12/23 5:23
 */
public class TransactionInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        try {
            System.out.println("事务开始");
            result = methodProxy.invokeSuper(obj, args);
            System.out.println("事务结束,提交");
        } catch (Throwable throwable) {
            System.out.println("事务异常,回滚");
            throw throwable;
        }
        return result;
    }
}

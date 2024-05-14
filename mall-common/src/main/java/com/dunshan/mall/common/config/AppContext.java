package com.dunshan.mall.common.config;

import com.alibaba.ttl.TransmittableThreadLocal;

import java.io.Serializable;

/**
 * @author zhenghf
 * @date 2024-05-14
 * @desc
 */

public class AppContext implements Serializable {
    private static final TransmittableThreadLocal<AppContext> APP_CONTEXT = new TransmittableThreadLocal<>();
    private String flag;

    public static AppContext getContext() {
        return APP_CONTEXT.get();
    }

    public static void setContext(AppContext context) {
        APP_CONTEXT.set(context);
    }
    public static void removeContext() {
        APP_CONTEXT.remove();
    }

    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
}

package com.yupi.springbootinit.common;

public class BaseContext {

    public static ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Object id) {
        threadLocal.set(id);
    }

    public static Object getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }

}
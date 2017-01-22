package com.beatago.net.log;

public class Log {
    /**
     * 调试日志
     * @param object 消息
     */
    public static void logD(Object object) {
        System.out.println(object);
    }

    /**
     * 错误日志
     * @param object 消息
     */
    public static void logE(Object object) {
        System.out.println(object);
    }
}


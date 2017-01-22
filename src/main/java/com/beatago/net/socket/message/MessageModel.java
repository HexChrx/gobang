package com.beatago.net.socket.message;

import java.util.HashMap;

public class MessageModel {

    public static final int MESSAGE_TYPE_LOGIN = 1;
    public static final int MESSAGE_TYPE_MSG = 2;
    public static final int MESSAGE_TYPE_MOVE = 3;

    private int type;

    private int errno;

    private HashMap<String, String> content;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public HashMap<String, String> getContent() {
        return content;
    }

    public void setContent(String key, String content) {
        if (this.content == null) {
            this.content = new HashMap<>();
        }
        this.content.put(key, content);
    }

    public String get(String key) {
        return content.get(key);
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }
}


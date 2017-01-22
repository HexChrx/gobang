package com.beatago.net.socket.message;

import com.beatago.net.core.Status;
import com.beatago.net.util.Json;


public class MessageProcess implements Runnable {

    private String message;

    public MessageProcess(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        MessageModel messageModel = (MessageModel) Json.decode(new MessageModel(), message);
        switch (messageModel.getType()) {
            case MessageModel.MESSAGE_TYPE_MSG:
                if (messageModel.getErrno() == Status.OK) {

                }
                break;
            case MessageModel.MESSAGE_TYPE_MOVE:
                break;
        }
    }
}

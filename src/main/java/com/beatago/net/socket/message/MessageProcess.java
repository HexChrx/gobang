package com.beatago.net.socket.message;

import android.util.Log;
import com.beatago.net.core.Status;
import com.beatago.net.service.UserService;
import com.beatago.net.util.Json;


public class MessageProcess implements Runnable {

    private String message;

    public MessageProcess(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        Log.d("debug", message);
        MessageModel messageModel = (MessageModel) Json.decode(new MessageModel(), message);
        if (messageModel != null) {
            switch (messageModel.getType()) {
                case MessageModel.MESSAGE_TYPE_LOGIN:
                    if (messageModel.getErrno() == Status.LOGIN_SUCCESS) {
                        UserService.getInstance().setLoginState(Status.LOGIN_SUCCESS);
                    }
                    break;
                case MessageModel.MESSAGE_TYPE_MSG:
                    if (messageModel.getErrno() == Status.OK) {
                        UserService.getInstance().setLoginState(Status.LOGIN_SUCCESS);
                    }
                    break;
                case MessageModel.MESSAGE_TYPE_MOVE:
                    break;
            }
        }
    }
}

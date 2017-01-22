package com.beatago.net.service;

import com.beatago.net.core.Status;
import com.beatago.net.model.UserModel;
import com.beatago.net.socket.SocketLink;
import com.beatago.net.socket.message.MessageModel;
import com.beatago.net.util.Json;

public class UserService {
    private int loginState;

    private static UserService instance;
    private UserModel user;

    private UserService(){
        loginState = 0;
    }

    public static UserService getInstance(){
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        SocketLink socketLink;
        socketLink = SocketLink.getInstence();
        if (socketLink != null) {
            MessageModel messageModel = new MessageModel();
            messageModel.setType(MessageModel.MESSAGE_TYPE_LOGIN);
            messageModel.setContent("uid", username);
            messageModel.setContent("pwd", password);
            socketLink.send(Json.encode(messageModel));
        }
        int i = 0;
        for(; i < 10; i++) {
            if (this.loginState == Status.LOGIN_SUCCESS) {
                return true;
            } else if (this.loginState == Status.LOGIN_PASSPORD_ERROR) {
                return false;
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
        }
        this.setLoginState(Status.LOGIN_TIME_OUT);
        return false;
    }

    public void setLoginState(int loginState) {
        this.loginState = loginState;
    }

    public int getLoginState(){
        return this.loginState;
    }
}

package com.beatago.net.socket;

import com.beatago.net.core.Base;
import com.beatago.net.log.Log;
import com.beatago.net.socket.message.MessageProcess;

import java.io.*;
import java.net.Socket;

public class SocketLink extends Base {
    private static final int PORT = 8741;
    private static final String IP = "10.95.25.20";

    private PrintWriter writer;
    private BufferedReader reader;

    private Socket socket;

    private static SocketLink instence;

    private SocketLink() throws IOException {
        this.socket = new Socket(SocketLink.IP, SocketLink.PORT);
        OutputStream os = socket.getOutputStream();//字节输出流
        this.writer = new PrintWriter(os);//将输出流包装为打印流
        InputStream is = socket.getInputStream();
        this.reader = new BufferedReader(new InputStreamReader(is));
        new Read().start();
    }

    public static SocketLink getInstence () {
        try {
            if (SocketLink.instence == null) {
                SocketLink.instence = new SocketLink();
            }
        } catch (IOException e) {
            SocketLink.instence = null;
            Log.logE(e.getMessage());
        }
        return SocketLink.instence;
    }

    class Read extends Thread {
        @Override
        public void run() {
            super.run();
            String info;
            try {
                while ((info = reader.readLine()) != null) {
                    android.util.Log.d("debug", info);
                    new Thread(new MessageProcess(info)).start();
                }
            } catch (IOException e) {
                instence = null;
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送消息
     * @param message String
     * @return boolean
     */
    public boolean send(String message) {
        if (writer != null) {
            writer.write(message);
            writer.flush();
            return true;
        }
        return false;
    }
}

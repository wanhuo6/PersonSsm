package com.ahuo.spring.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by wanhuo on 2017-10-13.
 */
public class Client {

    public static final String CHARCODE = "utf-8";

    public static void main(String[] args) {

        Socket socket = null;
        int port = 8821;

        OutputStream socketOut = null;
        BufferedReader br = null;
        try {
            socket = new Socket("192.168.21.194", port);
            // 发送消息
            String msg = "客户端发送2";
            // base64 编码，防止中文乱码
            msg = Util.encode(msg.getBytes(CHARCODE));
            System.out.println("c1:" + msg);
            System.out.println("c2:" + Util.decode(msg,CHARCODE));

            msg = msg + "\r\n";
            socketOut = socket.getOutputStream();
            socketOut.write(msg.getBytes(CHARCODE));
            socketOut.flush();

            // 接收服务器的反馈
            br = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            String res = br.readLine();
            if (res != null) {
                res = Util.decode(res,CHARCODE);
                System.out.println("c3:" + res);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (socketOut != null) {
                try {
                    socketOut.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}

package com.ahuo.spring.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wanhuo on 2017-10-13.
 */
public class MultiThreadServer {

    private int port = 8821;
    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private final int POOL_SIZE = 10;
    public static final String CHARCODE = "utf-8";

    public List<Socket> mSocketList = new ArrayList<>();

    public MultiThreadServer() throws IOException {
        serverSocket = new ServerSocket(port);
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                .availableProcessors() * POOL_SIZE);
        System.out.println("服务已启动");
    }

    private void sendAllSocket(String msg) throws Exception {
       /* for (int i = 0; i < mSocketList.size(); i++) {
            Socket socket = mSocketList.get(i);
            System.out.println(socket.isBound()+"=="+socket.isClosed()+"-+"+socket.getKeepAlive()+"----"+socket.getRemoteSocketAddress());
            if (socket.isClosed()) {
                System.out.println("断开"+socket.getRemoteSocketAddress());
                mSocketList.remove(socket);
                closeSocket(socket);
            }
        }*/
        for (int i = 0; i < mSocketList.size(); i++) {
            Socket socket = mSocketList.get(i);
            System.out.println(Thread.currentThread().getName() + "---" + msg);
            // 向客户端发送信息
            OutputStream output = null;
            try {
                output = socket.getOutputStream();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                output.write((msg + "\n").getBytes(CHARCODE));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 发送数据
            try {
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void closeSocket(Socket socket,BufferedReader br) {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }


    }

    public void service() {
        while (true) {
            //接受多socket
            Socket socket = null;
            try {
                System.out.println("ssssssssssssssss");
                socket = serverSocket.accept();
                mSocketList.add(socket);
                executorService.execute(new Handler(socket));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    public static void main(String[] args) throws IOException {
        new MultiThreadServer().service();
    }

    class Handler implements Runnable {


        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }


        public void run() {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                String content = null;
                try {
                    content = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (content != null) {
                    System.out.println(Util.decode(content, CHARCODE) + "---");
                    try {
                        if (Util.decode(content, CHARCODE).equals("exit")) {
                            System.out.println("remove");
                            mSocketList.remove(socket);
                            content = Util.encode(("玩家" + socket.getRemoteSocketAddress() + "退出聊天").getBytes());
                            closeSocket(socket,br);
                            sendAllSocket(content);
                            break;
                        }
                        sendAllSocket(content);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

}

package com.dfire.share.正确中断线程.不响应中断;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author baiyundou
 * @date 14:18 2019/11/22
 * @description
 */
public class ReaderThread extends Thread {
    private static final int BUFSZ = 1024;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    // 覆盖Thread类的interrupt方法, 加入关闭socket的代码
    // 如果发生中断时, 线程阻塞在read方法上, socket的关闭会导致read方法抛出SocketException, 然后run方法运行完毕
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {
        } finally {
            super.interrupt();
        }
    }

    public void run() {
        byte[] buf = new byte[BUFSZ];
        while (true) {
            int count = 0;
            try {
                count = in.read(buf);
            } catch (IOException e) {
                if(Thread.interrupted()){
                    //这个时候代表是我们中断了线程
                    return;
                }
            }
            if (count < 0)
                break;
            else if (count > 0)
                processBuffer(buf, count);
        }
    }

    private void processBuffer(byte[] buf, int count) {
        //...
    }
}
package com.ahuo.spring.test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by wanhuo on 2017-8-9.
 */
public class test {
    public static void main(String[] args) throws IOException {

        //  ByteBuffer header = ByteBuffer.allocate(128);
        //   ByteBuffer body   = ByteBuffer.allocate(1024);

        //  ByteBuffer[] bufferArray = { header, body };
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile("C:\\Users\\wanhuo\\Desktop\\2333.txt", "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        FileChannel inChannel = aFile.getChannel();
//create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = 0; //read into buffer.
        try {
            bytesRead = inChannel.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (bytesRead != -1) {
            buf.flip();  //make buffer ready for read
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get()); // read 1 byte at a time
            }
            buf.clear(); //make buffer ready for writing
            try {
                bytesRead = inChannel.read(buf);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            aFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        RandomAccessFile fromFile = new RandomAccessFile("C:\\Users\\wanhuo\\Desktop\\2333.txt", "rw");
        FileChannel      fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("C:\\Users\\wanhuo\\Desktop\\2334.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();
        long position = 0;
        long count =6;
        toChannel.transferFrom(fromChannel,position, count);


    }
}

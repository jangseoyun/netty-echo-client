package com.netty.clientserver.sockets;

import com.google.common.io.ByteStreams;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

@AllArgsConstructor
public class ClientSocket {
    private Socket socket;

    public void sendFixedLength(int messageLength) {
        int delimiterLength = 256;

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < messageLength; i++) {
            stringBuilder.append("A");
        }
        byte[] totalData = stringBuilder.toString().getBytes();
        System.out.println("Sending message");

        try {
            OutputStream outputStream = socket.getOutputStream();

            for (int i = 0; i < messageLength / delimiterLength; i++) {
                byte[] sending = Arrays.copyOfRange(totalData, i * delimiterLength, (i + 1) * delimiterLength);
                System.out.println("Sending..." + (i + 1));
                outputStream.write(sending);
                outputStream.flush();
                Thread.sleep(500);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("Receiving message");
        try {
            InputStream inputStream = socket.getInputStream();

            byte[] reply = new byte[messageLength];
            ByteStreams.read(inputStream, reply, 0, reply.length);

            System.out.println(new String(reply));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

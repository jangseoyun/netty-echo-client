package com.netty.clientserver;

import com.netty.clientserver.sockets.NonSslSocket;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class NettyClientServerApplication {
    public static void main(String[] args) throws InterruptedException{
        String host = "host Number";
        int port = 8080;
        try {
            System.out.println("Enter message length: ");
            Scanner sc = new Scanner(System.in);
            int messageLength = Integer.parseInt(sc.nextLine());

            NonSslSocket socket = new NonSslSocket(host, port);
            socket.run(messageLength);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

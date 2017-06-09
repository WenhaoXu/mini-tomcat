package org.bbs4j.tomcat.origin;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by apple on 17/5/30.
 */
public class HttpServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8082, 1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                Processor.parse(socket);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println(e.toString());
            }
        }
    }
}

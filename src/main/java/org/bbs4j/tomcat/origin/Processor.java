package org.bbs4j.tomcat.origin;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by apple on 17/5/30.
 */
public class Processor {

    public static void parse(Socket socket) {
        try {
            InputStream in = socket.getInputStream();
            Request request = new Request(in);
            request.parse();
            Response response = new Response(socket.getOutputStream());
            response.sendResponse();
//            response.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }
}

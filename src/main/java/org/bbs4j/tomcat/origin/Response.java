package org.bbs4j.tomcat.origin;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 * Created by apple on 17/5/30.
 */
public class Response {

    private OutputStream out;
    public Response(OutputStream out) {
        this.out = out;
    }



    public PrintWriter getWriter() {
        return null;
    }

    public void sendResponse() {
        try {
            String errMsg = "HTTP/1.1 404 File Not Found\r\n"
                    + "Content-Type:text/html\r\n"
                    + "Content-Length:23\r\n"
                    + "\r\n"
                    + "<h1>File Not Found</h1>";
            out.write(errMsg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendResponse1() {
        try {
            String errMsg = "hello world";
            out.write(errMsg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (out!=null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

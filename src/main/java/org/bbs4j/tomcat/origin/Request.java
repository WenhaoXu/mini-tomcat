package org.bbs4j.tomcat.origin;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by apple on 17/5/30.
 */
public class Request {
    private  InputStream in;
    public Request(InputStream in) {
        this.in = in;
    }

    public void parse() {
        StringBuilder sb  = new StringBuilder();
        byte[] buf = new byte[512];
        try {
            int i = in.read(buf);
            for (int j = 0; j <  i;j++ ) {
                sb.append((char) buf[j]);
            }
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println(e.toString());
        }
    }
}

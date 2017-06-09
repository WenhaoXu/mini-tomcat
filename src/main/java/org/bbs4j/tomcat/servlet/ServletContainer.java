package org.bbs4j.tomcat.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuzhouwang on 17/6/9.
 */
public class ServletContainer {

    private Map<String,Class> servletMapping = new HashMap<String, Class>();

    public static void main(String[] args) {
        new ServletContainer().start();

    }

    public void start() {
        //load the config and the class
        //initial the servlet

        //bind the port
        try {
            ServerSocket serverSocket = new ServerSocket(8082);
            while (true) {
                Socket socket = serverSocket.accept();
                dispatch(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void dispatch(Socket socket) {
        //parse http
        try {
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            Request request = new Request(in);
            request.parse();
            Response response = new Response(out);
            doServlet(request, response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doServlet(Request request, Response response) {
        HttpServletRequest httpServletRequest = toHttpServletRequest(request);
        Class servletClass = servletMapping.get(httpServletRequest.getRequestURI());
        if (servletClass.isAssignableFrom(Servlet.class )) {
            try {
                HttpServlet httpServlet =(HttpServlet) servletClass.newInstance();
                httpServlet.service(request,response);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            response.notFoundPage();
        }

    }

    private HttpServletRequest toHttpServletRequest(Request request) {
        return null;
    }
}

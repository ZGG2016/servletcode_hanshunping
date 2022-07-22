package com.example.servlet.helloservlet;

import java.io.*;
import javax.servlet.http.*;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

//        String spath  = "http://www.zlcool.com/d/file/2014/10/29/819554322f4c778f0c5bf5b079192bad.gif";

        PrintWriter out = response.getWriter();
        out.println("<html><body>");

        out.println("<h1>" + message + "</h1>");
//        out.println("<img src=" + spath + "><br>");
        out.println("<img src=http://www.zlcool.com/d/file/2014/10/29/819554322f4c778f0c5bf5b079192bad.gif width=120 height=30><br>");

        out.println("</body></html>");
    }

    public void destroy() {
    }

}
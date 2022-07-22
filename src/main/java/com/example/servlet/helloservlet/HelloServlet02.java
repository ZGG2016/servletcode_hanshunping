package com.example.servlet.helloservlet;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "helloServlet02", value = "/hello-servlet02")
public class HelloServlet02 extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter pw = servletResponse.getWriter();
        pw.println("hello servlet 02");
    }
}

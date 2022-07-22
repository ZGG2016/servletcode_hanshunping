package com.example.servlet.helloservlet;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet(name = "helloServlet01", value = "/hello-servlet01")
public class HelloServlet01 implements Servlet {

    // 初始化该servlet（类似于类的构造函数）
    // 该函数只会被调用一次（当用户第一次访问该servlet时被调用）
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init it");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    // 该函数用于处理业务逻辑
    // 当用户每访问该servlet方法时，都会调用
    // req 用于获得客户端的信息   res 用于向客户端返回信息

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        PrintWriter pw = servletResponse.getWriter();
        pw.println("hello servlet 01");
    }

    @Override
    public String getServletInfo() {
        return " ";
    }

    // 销毁该servlet实例，在以下情况被调用
    // tomcat重新启动； reload该webapp； 重新启动电脑
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}

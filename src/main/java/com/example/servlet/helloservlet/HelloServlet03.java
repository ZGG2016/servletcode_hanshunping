package com.example.servlet.helloservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 用户发出post请求，doPost方法进行处理，实际的业务逻辑在doGet执行
public class HelloServlet03 extends HttpServlet {

    // 处理get请求
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            PrintWriter pw = resp.getWriter();
            pw.println("hello servlet 03");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 处理post请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

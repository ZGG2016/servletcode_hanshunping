package com.example.servlet.session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
/*
* 用户通过浏览器访问网站，服务器会在服务器内存为该浏览器分配一个空间，
* 该空间被这个浏览器独占，该空间就是session空间
* */
public class SessionTest01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("text/html; charset=gbk");
            PrintWriter pw = resp.getWriter();

            // 得到和req相关联的session
            HttpSession hs = req.getSession(true);
            String sessionid = hs.getId();
            pw.println("SessionTest01 的 session id 是 " + sessionid+"<br>");

            String name = "color";
            String value = "red";
            // 在该session中添加属性
            hs.setAttribute(name,value);
            hs.setMaxInactiveInterval(30);

            pw.println("在 sessionid 为 " + sessionid + " 的 session 中添加属性 "+ name + ":" + value);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

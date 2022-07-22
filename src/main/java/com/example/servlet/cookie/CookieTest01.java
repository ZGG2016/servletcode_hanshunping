package com.example.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/*
服务器在客户端保存用户的信息，如登录名、密码
 */
public class CookieTest01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("text/html; charset=gbk");

            PrintWriter pw = resp.getWriter();
            // 当用户访问该servlet时，将信息创建到该用户的cookie中
            // 在服务器端创建一个cookie
            Cookie cookie = new Cookie("color","red");
            // 不设置时间就不会保存该cookie
            cookie.setMaxAge(120);
            //将cookie回写到客户端
            resp.addCookie(cookie);
            pw.println("已创建cookie");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

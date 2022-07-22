package com.example.servlet.loginsystem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("text/html; charset=gbk");

            PrintWriter pw = resp.getWriter();

            // 返回登录页面
            pw.println("<html><body>");

            String info = req.getParameter("info");
            if(info!=null){
                pw.println("<h1>你的用户名或密码错误</h1><br>");
            }
            pw.println("<h1>登录页面<h1>");

            pw.println("<form action=loginauth method=post>");
            pw.println("用户名： <input type=text name=username><br>");
            pw.println("密码： <input type=password name=passwd><br>");
            pw.println("<input type=checkbox name=keep value=2 style='font-size:10px'>两周内不再重新登录<br>");
            pw.println("<input type=submit name=login><br>");
            pw.println("<form>");

            pw.println("</body></html>");

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

package com.example.servlet.loginsystem02;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Err extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("text/html; charset=gbk");

            PrintWriter pw = resp.getWriter();

            // 返回登录页面
            pw.println("<html><body bgcolor=#CED3FF>");

            pw.println("<img src=http://www.zlcool.com/d/file/2014/10/29/819554322f4c778f0c5bf5b079192bad.gif width=150 height=40><center><hr>");

            pw.println("<h1>操作失败</h1>");

            pw.println("</center><hr><img src=http://www.zlcool.com/d/file/2014/10/29/819554322f4c778f0c5bf5b079192bad.gif width=150 height=40>");


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

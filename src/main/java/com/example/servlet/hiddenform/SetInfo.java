package com.example.servlet.hiddenform;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*
* 通过隐藏表单可以将一个页面信息传给另外的页面
*
* */
public class SetInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            resp.setContentType("text/html; charset=gbk");

            PrintWriter pw = resp.getWriter();

            String sexval = "male";

            pw.println("<html><body>");
            pw.println("<h1>登录界面</h1>");
            pw.println("<form action=getinfo method=post>");
            pw.println("用户名： <input type=text name=username><br>");
            pw.println("密码： <input type=password name=password><br>");
            pw.println("<input type=hidden name=sex value="+sexval+"><br>");
            pw.println("<input type=submit name=submit><br>");
            pw.println("</form>");
            pw.println("</body></html>");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

package com.example.servlet.loginsystem02;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Update extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("text/html; charset=gbk");

            PrintWriter pw = resp.getWriter();

            pw.println("<html><body bgcolor=#CED3FF>");

            pw.println("<img src=http://www.zlcool.com/d/file/2014/10/29/819554322f4c778f0c5bf5b079192bad.gif width=150 height=40><center><hr>");

            pw.println("<h1>修改用户界面</h1>");

            pw.println("<form action=updateuser>");
            pw.println("<table border=1>");
            pw.println("<tr><td>userid</td><td><input readonly name=userid type=text value= " + req.getParameter("userid") + "></td></tr>");
            pw.println("<tr><td>username</td><td><input readonly name=username type=text value= " + req.getParameter("username") + "></td></tr>");
            pw.println("<tr><td>password</td><td><input name=password type=text value= " + req.getParameter("password") + "></td></tr>");
            pw.println("<tr><td>email</td><td><input name=email type=text value= " + req.getParameter("email") + "></td></tr>");
            pw.println("<tr><td>grade</td><td><input name=grade type=text value= " + req.getParameter("grade") + "></td></tr>");

            pw.println("<tr><td colspan=2><input type=submit value=修改用户></td></tr>");
            pw.println("</table></form>");
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

package com.example.servlet.hiddenform;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GetInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            resp.setContentType("text/html; charset=gbk");
            PrintWriter pw = resp.getWriter();

            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String sex = req.getParameter("sex");

            pw.println("username = " + username + "\n"
                        + "password = " + password + "\n"
                    + "sex = " + sex + "\n");   // 得到隐藏的性别名

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

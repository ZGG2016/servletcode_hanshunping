package com.example.servlet.loginsystem02;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DelUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("text/html; charset=gbk");

            String userid = req.getParameter("userid");

            UserBeanCli ubc = new UserBeanCli();
            if(ubc.deleteUser(userid)){
                resp.sendRedirect("ok");
            }else{
                resp.sendRedirect("err");
            }


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

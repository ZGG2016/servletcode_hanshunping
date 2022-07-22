package com.example.servlet.session;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionTest02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            resp.setContentType("text/html; charset=gbk");
            PrintWriter pw = resp.getWriter();

            HttpSession hs = req.getSession(true);
            String sessionid = hs.getId();
            String color = (String) hs.getAttribute("color");

            pw.println("sessionid 为 "+ sessionid +" 的属性 color 的值为："+ color);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

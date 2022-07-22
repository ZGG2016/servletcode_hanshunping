package com.example.servlet.session;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionTest03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            resp.setContentType("text/html; charset=gbk");
            PrintWriter pw = resp.getWriter();

            HttpSession hs = req.getSession(true);
            String sessionid = hs.getId();

            hs.setAttribute("1","zhangsan");
            hs.setAttribute("2","lisi");

            pw.println("当前属性有：<br>" + (String) hs.getAttribute("1") + " " + (String) hs.getAttribute("2")+"<br>");


            hs.removeAttribute("2");
            pw.println("当前属性有：<br>" + (String) hs.getAttribute("1") + " " + (String) hs.getAttribute("2"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

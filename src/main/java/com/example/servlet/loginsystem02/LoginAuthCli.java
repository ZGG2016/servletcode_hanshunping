package com.example.servlet.loginsystem02;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class LoginAuthCli extends HttpServlet {

    String path = "D:\\file\\servletcode\\src\\main\\java\\com\\example\\servlet\\loginsystem02\\counter.txt";

    @Override
    public void init() throws ServletException {
        // 增加计数器功能
        try {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String ss = br.readLine();

            ServletContext sc = this.getServletContext();
            sc.setAttribute("visittimes",ss);
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {

        try {
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            String times = this.getServletContext().getAttribute("visittimes").toString();
            bw.write(times);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("text/html; charset=gbk");

            String username = req.getParameter("username");
            String password = req.getParameter("passwd");

            UserBeanCli ubc = new UserBeanCli();

            if(ubc.checkUser(username,password)){

                // 保存到cookie
                String keep = req.getParameter("keep");
                if(keep!=null){
                    Cookie name = new Cookie("myname",username);
                    Cookie passwd = new Cookie("mypasswd",password);
                    name.setMaxAge(14*24*3600);
                    passwd.setMaxAge(14*24*3600);
                    resp.addCookie(name);
                    resp.addCookie(passwd);
                }


                // 将验证成功的信息写入session
                HttpSession session = req.getSession(true);
                session.setMaxInactiveInterval(30);
                session.setAttribute("username",username);
                session.setAttribute("passwd",password);

                String times = this.getServletContext().getAttribute("visittimes").toString();

                this.getServletContext().setAttribute("visittimes",Integer.parseInt(times)+1);

                resp.sendRedirect("main");

            }else{
                resp.sendRedirect("loginn");
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

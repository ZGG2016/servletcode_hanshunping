package com.example.servlet.loginsystem;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginAuth extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            resp.setContentType("text/html; charset=gbk");

            // 接收用户名密码
            // Login.java 里的 用户名 name=username 中的 username
            String username = req.getParameter("username");
            String password = req.getParameter("passwd");

            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","root");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.1.6:3306/test_db","root","root");
            stmt = conn.createStatement();

            // 注意如何拼接
//            String sql = "select * from userdb.users where username='"+username+ "' and password='" +passwd+ "'";
            // 防止sql注入  select * from users where username="sda" and password="cc" or 1="1";
            // 通过用户名匹配密码，再和输入的密码匹配
            String sql = "select password from users where username='"+username+ "'";
            rs = stmt.executeQuery(sql);

            if(rs.next()){

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

                String dbpasswd = rs.getString("password");
                if(dbpasswd.equals(password)){

                    // 将验证成功的信息写入session
                    HttpSession session = req.getSession(true);
                    session.setMaxInactiveInterval(30);
                    session.setAttribute("username",username);
                    session.setAttribute("passwd",password);

                    // 写你要到的servlet的url
                    // resp.sendRedirect("welcome");

                    // 同一用户的不同页面共享数据：sendRedirect("welcome?uname=shunping&pass=ok");
                    resp.sendRedirect("welcome?uname="+username+"&upasswd="+password);
                }
            }else{
                resp.sendRedirect("login");
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {

            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }
    // 处理post请求
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

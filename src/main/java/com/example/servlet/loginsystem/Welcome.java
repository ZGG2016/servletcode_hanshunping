package com.example.servlet.loginsystem;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Welcome extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            resp.setContentType("text/html; charset=gbk");

            HttpSession session = req.getSession(true);
            String uval = (String) session.getAttribute("username");
            String pval = (String) session.getAttribute("passwd");

            PrintWriter pw = resp.getWriter();

            String name = "";
            String passwd = "";

            // 用户登录成功后，在该浏览器下session记录用户名和密码，直接输入welcome的链接，能够直接登录
            // 如果换一个浏览器的话，就会因为新浏览器没保存用户名和密码，而跳转回登录页面
            if(uval==null && pval==null){

                // 如果session中没有用户信息，再判断cookie中有没有
                Cookie[] cookies = req.getCookies();

                int i=0;
                if(cookies.length!=0){
                    for(;i<cookies.length;i++){
                        Cookie cs = cookies[i];
                        if("myname".equals(cs.getName())&&"mypasswd".equals(cs.getName())){
                             name = cs.getValue();
                             passwd = cs.getValue();
                        }
                    }
                    if(!"".equals(name)&&!"".equals(passwd)){
                        resp.sendRedirect("loginauth?username="+name+"&password="+passwd);
                        return;
                    }
                }

                resp.sendRedirect("login?info=error");
                return;
            }

            pw.println("<body><center>");

            // 图片不显示？？？
//            pw.println("<img src=src/main/webapp/imgs/pic.jpg><br>");
            pw.println("welcome "+uval+" "+pval+"<br>");
            pw.println("<a href=login>返回重新登录</a>");

            // ================ 分页功能 ================
            int pageSize = 3; // 一页显示几行
            int pageNow = 1;  // 当前显示第几页
            int rowCount = 0; // 总行数
            int pageCount = 0;// 总页数

            // 动态接收pagenow
            String dpagenow = req.getParameter("pagenow");
            if(dpagenow!=null){
                pageNow = Integer.parseInt(dpagenow);
            }


            Class.forName("com.mysql.cj.jdbc.Driver");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","root");
            conn = DriverManager.getConnection("jdbc:mysql://192.168.1.6:3306/test_db","root","root");
            ps = conn.prepareStatement("select count(*) from users");
            rs = ps.executeQuery();
            if(rs.next()){
                rowCount = rs.getInt(1); // 总行数  17
            }
            // 计算总页数  17 % 3 = 5...2
            if(rowCount%pageSize==0){
                pageCount = rowCount/pageSize;
            }else{
                pageCount = rowCount/pageSize + 1;
            }
            //
            // 计算翻到的这一页显示哪几条数据
            ps = conn.prepareStatement("select * from users " +
                    "where userId in (" +
                        "select userId from (" +
                            "select userId from users " +
                                "limit "+pageSize*(pageNow-1)+","+pageSize+") t)");
            rs = ps.executeQuery();

            pw.println("<table border=1>");
            pw.println("<tr>" +
                    "<td>userId</td>" +
                    "<td>username</td>" +
                    "<td>password</td>" +
                    "<td>email</td>" +
                    "<td>grade</td>" +
                    "</tr>");
            while(rs.next()){
                pw.println("<tr>");
                pw.println("<td>"+rs.getInt(1)+"</td>");
                pw.println("<td>"+rs.getString(2)+"</td>");
                pw.println("<td>"+rs.getString(3)+"</td>");
                pw.println("<td>"+rs.getString(4)+"</td>");
                pw.println("<td>"+rs.getInt(5)+"</td>");
                pw.println("</tr>");
            }

            pw.write("</table>");

            if(pageNow!=1){
                pw.println("<a href=welcome?pagenow="+(pageNow-1)+">"+"上一页</a>");
            }

//            for(int i=1;i<=pageCount;i++){
            for(int i=pageNow;i<=pageNow+pageSize-1;i++){
                pw.println("<a href=welcome?pagenow="+i+">"+i+"</a> ");
            }
            if(pageNow!=pageCount){
                pw.println("<a href=welcome?pagenow="+(pageNow+1)+">"+"下一页</a>");
            }

            pw.println("<br>");

            pw.println("</center></body>");
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

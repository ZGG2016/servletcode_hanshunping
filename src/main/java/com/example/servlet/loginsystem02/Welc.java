package com.example.servlet.loginsystem02;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Welc extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
                        resp.sendRedirect("loginauthcli?username="+name+"&password="+passwd);
                        return;
                    }
                }

                resp.sendRedirect("loginn?info=error");
                return;
            }

            pw.println("<html><body bgcolor=#CED3FF>");

            pw.println("<img src=http://www.zlcool.com/d/file/2014/10/29/819554322f4c778f0c5bf5b079192bad.gif width=150 height=40> " +
                    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "hello " + uval +
                    "<img src=https://demosc.chinaz.net/Files/pic/iconsico/5949/q1.ico width=35 height=35><center><hr>");

            pw.println("<h1>管理用户</h1>");
//            pw.println("hello "+uval+"<br>");
            pw.println("<a href=loginn>返回重新登录</a><br>");


            // ================ 分页功能 ================
            int pageSize = 3; // 一页显示几行
            int pageNow = 1;  // 当前显示第几页

            // 动态接收pagenow
            String dpagenow = req.getParameter("pagenow");
            if(dpagenow!=null){
                pageNow = Integer.parseInt(dpagenow);
            }

            UserBeanCli ubc = new UserBeanCli();
            ArrayList<UserBean> al = ubc.getResultByPage(pageSize,pageNow);
            int pageCount = ubc.getPageCount();

            pw.println("<table border=1>");
            pw.println("<tr bgcolor=pink>" +
                    "<td>userId</td>" +
                    "<td>username</td>" +
                    "<td>password</td>" +
                    "<td>email</td>" +
                    "<td>grade</td>" +
                    "<td>修改用户</td>" +
                    "<td>删除用户</td>" +
                    "</tr>");

            String[] color = {"silver","pink"};

//            for (UserBean ub : al) {
            for (int i=0;i<al.size();i++) {

                UserBean ub = al.get(i);

                pw.println("<tr bgcolor=" + color[i%2] + ">");
                pw.println("<td>" + ub.getUserId() + "</td>");
                pw.println("<td>" + ub.getUsername() + "</td>");
                pw.println("<td>" + ub.getPassword() + "</td>");
                pw.println("<td>" + ub.getEmail() + "</td>");
                pw.println("<td>" + ub.getGrade() + "</td>");
                pw.println("<td><a href=update?" +
                           "userid=" + ub.getUserId() +
                           "&username=" + ub.getUsername() +
                           "&password=" + ub.getPassword() +
                           "&email=" + ub.getEmail() +
                           "&grade=" + ub.getGrade() +">修改用户</a></td>");
                pw.println("<td><a href=deluser?userid=" + ub.getUserId() + " onclick=\"return window.confirm('确认删除')\">删除用户</a></td>");
                pw.println("</tr>");
            }

            pw.write("</table>");

            if(pageNow!=1){
                pw.println("<a href=welc?pagenow="+(pageNow-1)+">"+"上一页</a>");
            }

            for(int i=pageNow;i<=pageNow+pageSize-1;i++){
                pw.println("<a href=welc?pagenow="+i+">"+i+"</a> ");
            }

            if(pageNow!=pageCount){
                pw.println("<a href=welc?pagenow="+(pageNow+1)+">"+"下一页</a>");
            }

            pw.println("<br>");

            pw.println("该网页被访问了"+this.getServletContext().getAttribute("visittimes").toString()+"次<br>");
            pw.println("您的 IP 地址为 "+req.getRemoteAddr()+"<br>");
            pw.println("您的机器名称为 "+req.getRemoteHost()+"<br>");

            // 判断是否是有效数字，是否数字过大
            pw.println("<form action=welc>");
            pw.println("跳转到：<input type=text name=pagenow>");
            pw.println("<input type=submit value=go>");

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

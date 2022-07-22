package com.example.servlet.cookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CookieTest02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            resp.setContentType("text/html; charset=gbk");

            PrintWriter pw = resp.getWriter();
            Cookie[] cookies = req.getCookies();

            int i=0;
            if(cookies.length!=0){
                for(;i<cookies.length;i++){
                    Cookie cs = cookies[i];
                    if("color".equals(cs.getName())){
                        String val = cs.getValue();
                        pw.println(val);
                        break;
                        // 可以通过设置 cookie.setMaxAge(0) 删除cookie
                    }
                }
                if(cookies.length==i){
                    pw.println("cookie已过期");
                }
            }else{
                pw.println("不存在这个cookie，或已过期");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

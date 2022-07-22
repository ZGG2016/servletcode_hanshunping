package com.example.servlet.servletfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
/*
*  servlet中操作文件和普通java操作文件一样
* */
public class SFileTest01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            resp.setContentType("text/html; charset=gbk");
            PrintWriter pw = resp.getWriter();

            FileReader fr = new FileReader("D:\\file\\后端\\servletcode\\src\\main\\java\\com\\example\\servlet\\servletfile\\test.txt");
            BufferedReader br = new BufferedReader(fr);

            String ss = br.readLine();
            br.close();
            pw.println(ss);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

package com.example.servlet.servletfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class SFileTest02 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            resp.setContentType("text/html; charset=gbk");
            PrintWriter pw = resp.getWriter();

            FileWriter fw = new FileWriter("D:\\file\\后端\\servletcode\\src\\main\\java\\com\\example\\servlet\\servletfile\\test.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("asewfwe");
            bw.close();
            pw.println("在文件中写入了数据");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

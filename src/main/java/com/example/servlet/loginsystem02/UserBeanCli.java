package com.example.servlet.loginsystem02;

import java.sql.*;
import java.util.ArrayList;

/*
* 处理Users表 <--> 操作UserBean
* 处理业务逻辑
* */
public class UserBeanCli {

    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private int pageCount = 0;

    public int getPageCount(){
        return this.pageCount;
    }

    public boolean updateUser(String userid, String password, String email, String grade){
        boolean res = false;

        try {
            ConnDB cn = new ConnDB();
            conn = cn.getConn();

            String sql = "update users set password='"+password+"',email='" +email + "',grade='" +grade+ "' where userId ='"+userid+ "'";

            stmt = conn.prepareStatement(sql);

            int num = stmt.executeUpdate();
            if(num==1){
                res = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }

        return res;

    }

    public boolean deleteUser(String userid){
        boolean res = false;

        try {
            ConnDB cn = new ConnDB();
            conn = cn.getConn();

            String sql = "delete from users where userId='"+ userid + "'";
            stmt = conn.prepareStatement(sql);

            int num = stmt.executeUpdate();
            if(num==1){
                res = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }

        return res;

    }

    public ArrayList<UserBean> getResultByPage(int pageSize,int pageNow){

        ArrayList<UserBean> al = new ArrayList<UserBean>();

        try{
            int rowCount = 0;
//            int pageCount = 0;

            ConnDB cn = new ConnDB();
            conn = cn.getConn();

            stmt = conn.prepareStatement("select count(*) from users");
            rs = stmt.executeQuery();
            if(rs.next()){
                rowCount = rs.getInt(1);
            }

            if(rowCount%pageSize==0){
                pageCount = rowCount/pageSize;
            }else{
                pageCount = rowCount/pageSize + 1;
            }
            // 如果不加一层子查询
            // 执行拼接的语句出现:
            // `java.sql.SQLSyntaxErrorException: 
            // This version of MySQL doesn't yet support` 错误。

            stmt = conn.prepareStatement("select * from users " +
                    "where userId in (" +
                    "select userId from (" +
                    "select userId from users " +
                    "limit "+pageSize*(pageNow-1)+","+pageSize+") t)");
            rs = stmt.executeQuery();

            while (rs.next()){
                UserBean ub = new UserBean();
                ub.setUserId(rs.getInt(1));
                ub.setUsername(rs.getString(2));
                ub.setPassword(rs.getString(3));
                ub.setEmail(rs.getString(4));
                ub.setGrade(rs.getInt(5));

                al.add(ub);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.close();
        }
        return al;
    }

    // 验证用户
    public boolean checkUser(String username,String password){
        boolean res = false;

        try {
            ConnDB cn = new ConnDB();
            conn = cn.getConn();

            String sql = "select password from users where username='"+username+ "'";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery(sql);
            if(rs.next()){
                String dbpasswd = rs.getString("password");
                if(dbpasswd.equals(password)){
                    res = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }

        return res;
    }

    public void close(){
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

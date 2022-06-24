package com.lsx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

//JDBC
public class jdbc_demo1 {
  public static void main(String[] args) throws Exception {
    //1.加载驱动
      Class.forName("com.mysql.jdbc.Driver");
      //2.获取连接
      Connection conn= DriverManager.getConnection(
              "jdbc:mysql://127.0.0.1:3306/javademo?characterEncoding=UTF8&autoReconnect=true&useSSL=false",
              "root",
              "lishixue2003");
      System.out.println("连接成功");
    String sql = "UPDATE student SET `name` =\"lsx1\" WHERE id = 1;";
      //3.执行sql
      Statement stmt=conn.createStatement();    //创建Statement对象
      //4.处理结果
      //4.1获取结果集
      int count = stmt.executeUpdate(sql);  //执行sql语句，返回受影响的行数
      //4.2遍历结果集
      System.out.println("受影响的行数："+count);
      //4.3关闭结果集
      //4.4关闭连接
      stmt.close();
      conn.close();

  }
}

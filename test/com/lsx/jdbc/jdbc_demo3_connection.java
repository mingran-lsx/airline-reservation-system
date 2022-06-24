package com.lsx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
//Connection的使用
//
//JDBC
public class jdbc_demo3_connection {
  public static void main(String[] args) throws Exception {
    //1.加载驱动
//      Class.forName("com.mysql.jdbc.Driver");
      //2.获取连接
      Connection conn= DriverManager.getConnection(
              "jdbc:mysql://127.0.0.1:3306/javademo?characterEncoding=UTF8&autoReconnect=true&useSSL=false",
              "root",
              "lishixue2003");
      System.out.println("连接成功");
    String sql = "UPDATE student SET `name` =\"lsx1\" WHERE id = 1;";
    String sql2 = "UPDATE student SET `name` =\"lsx2\" WHERE id = 2;";
      //3.获取执行sql语句的对象
      Statement stmt=conn.createStatement();    //创建Statement对象
      //4.执行sql语句
      int count = stmt.executeUpdate(sql);  //执行sql语句，返回受影响的行数
      try {
          //开启事务
          conn.setAutoCommit(false);
          //5.处理结果
          System.out.println("受影响的行数："+count);
          //4.执行sql语句
          int count2 = stmt.executeUpdate(sql);  //执行sql语句，返回受影响的行数
          //5.处理结果
          System.out.println("受影响的行数："+count2);
          //提交事务
          conn.commit();
      } catch (Exception e) {
          //回滚事务
          conn.rollback();
          //事务的回滚是指程序或数据处理错误，将程序或数据恢复到上一次正确状态的行为
          e.printStackTrace();
      }
      //4.3关闭结果集
      //4.4关闭连接
      stmt.close();
      conn.close();

  }
}

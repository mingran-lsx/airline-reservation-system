package com.lsx.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
//DriverManager 的作用是管理数据库连接 DriverManager.getConnection(url, user, password);   url:数据库连接地址 user:用户名  password:密码   返回值是Connection类型的对象
//Connection 的作用是管理数据库连接  Statement 的作用是管理数据库操作
//Connection 的方法：
//1.Connection.createStatement()  创建Statement对象     返回值是Statement对象     Statement对象的方法：     2.Statement.execute(String sql)  执行SQL语句    返回值是boolean类型的值 true:执行成功  false:执行失败   3.Statement.close()  关闭Statement对象      4.Connection.close()  关闭Connection对象
//DriverManager 的方法：
//1.DriverManager.getConnection(url, user, password)  获取数据库连接      返回值是Connection对象         2.DriverManager.registerDriver(Driver driver)  注册驱动程序      返回值是Driver对象        3.DriverManager.getDrivers()  获取驱动程序列表      返回值是Driver对象的列表    4.DriverManager.getDriver(url)  获取驱动程序      返回值是Driver对象  5.DriverManager.deregisterDriver(Driver driver)  取消注册驱动程序      返回值是Driver对象 6.DriverManager.setLoginTimeout(int seconds)  设置登录超时时间      返回值是void      7.DriverManager.getLoginTimeout()  获取登录超时时间      返回值是int
//DriverManager 的常用方法:
//
//DriverManager.getConnection(url, user, password)
//DriverManager.getConnection(url, info)
//DriverManager.getConnection(url)
//DriverManager.getConnection(url, user, password, props)
//DriverManager.getConnection(url, props)
//

//JDBC
public class jdbc_demo2_driverManager {
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

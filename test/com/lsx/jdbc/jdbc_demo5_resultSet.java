package com.lsx.jdbc;

import com.lsx.pojo.Student;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//Connection的使用
//
//JDBC
public class jdbc_demo5_resultSet {
    @Test //测试
  public  void test1() throws Exception {
    //1.加载驱动
//      Class.forName("com.mysql.jdbc.Driver");
      //2.获取连接
      Connection conn= DriverManager.getConnection(
              "jdbc:mysql://127.0.0.1:3306/javademo?characterEncoding=UTF8&autoReconnect=true&useSSL=false",
              "root",
              "lishixue2003");
      System.out.println("连接成功");
    String sql = "select * from student";   //查询操作
      //3.获取执行sql语句的对象
      Statement stmt=conn.createStatement();    //创建Statement对象是干嘛的？    创建Statement对象是为了执行sql语句

      try {
          //开启事务
          conn.setAutoCommit(false);
          //4.执行sql语句
          ResultSet res = stmt.executeQuery(sql);
          //5.处理结果
          while (res.next()) {  //next()方法是否还有下一条数据
              System.out.println(res.getString("id") + " " + res.getString("name") + " " + res.getString("avatar"));
          }
          //提交事务
          conn.commit();
      } catch (Exception e) {
          //回滚事务
          conn.rollback();
          //事务的回滚是指程序或数据处理错误，将程序或数据恢复到上一次正确状态的行为
          e.printStackTrace();  //打印异常信息
      }
      //4.3关闭结果集
      //4.4关闭连接
      stmt.close();
      conn.close();

  }
  //
    @Test
  public static void test2() throws Exception {
        //1.加载驱动
//      Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/javademo?characterEncoding=UTF8&autoReconnect=true&useSSL=false",
                "root",
                "lishixue2003");
        System.out.println("连接成功");
        String sql = "select * from student";   //查询操作
        //3.获取执行sql语句的对象
        Statement stmt = conn.createStatement();    //创建Statement对象是干嘛的？    创建Statement对象是为了执行sql语句

        ResultSet res = null;
        try {
            //开启事务
            conn.setAutoCommit(false);
            //4.执行sql语句
            res = stmt.executeQuery(sql);
            //5.处理结果

            List<Student> list = new ArrayList<>();
            while (res.next()) {  //next()方法是否还有下一条数据
                Student stu = new Student();    //创建一个Student对象
                stu.setId(res.getInt("id"));    //设置id
                stu.setId(res.getInt("student_id"));
                stu.setName(res.getString("name"));
                stu.setAvatar(res.getString("avatar"));
                stu.setPhone(res.getString("phone"));
                list.add(stu);
            }
            System.out.println(list);
            //提交事务
            conn.commit();
        } catch (Exception e) {
            //回滚事务
            conn.rollback();
            //事务的回滚是指程序或数据处理错误，将程序或数据恢复到上一次正确状态的行为
            e.printStackTrace();  //打印异常信息
        }
        //4.3关闭结果集
        //4.4关闭连接
        assert res != null; //断言    如果res为空，则抛出异常   如果res不为空，则执行下面的代码
        res.close();    //关闭结果集
        stmt.close();   //关闭Statement对象
        conn.close();   //关闭Connection对象

    }
}
//ResultSet+实体类
// class jdbc_demo_resultSet2 {
//    public static void main(String[] args) throws Exception {
//        //1.加载驱动
////      Class.forName("com.mysql.jdbc.Driver");
//        //2.获取连接
//        Connection conn = DriverManager.getConnection(
//                "jdbc:mysql://127.0.0.1:3306/javademo?characterEncoding=UTF8&autoReconnect=true&useSSL=false",
//                "root",
//                "lishixue2003");
//        System.out.println("连接成功");
//        String sql = "select * from student";   //查询操作
//        //3.获取执行sql语句的对象
//        Statement stmt = conn.createStatement();    //创建Statement对象是干嘛的？    创建Statement对象是为了执行sql语句
//
//        ResultSet res = null;
//        try {
//            //开启事务
//            conn.setAutoCommit(false);
//            //4.执行sql语句
//            res = stmt.executeQuery(sql);
//            //5.处理结果
//
//            List<Student> list = new ArrayList<>();
//            while (res.next()) {  //next()方法是否还有下一条数据
//                Student stu = new Student();    //创建一个Student对象
//                stu.setId(res.getInt("id"));    //设置id
//                stu.setId(res.getInt("student_id"));
//                stu.setName(res.getString("name"));
//                stu.setAvatar(res.getString("avatar"));
//                stu.setPhone(res.getString("phone"));
//                list.add(stu);
//            }
//            System.out.println(list);
//            //提交事务
//            conn.commit();
//        } catch (Exception e) {
//            //回滚事务
//            conn.rollback();
//            //事务的回滚是指程序或数据处理错误，将程序或数据恢复到上一次正确状态的行为
//            e.printStackTrace();  //打印异常信息
//        }
//        //4.3关闭结果集
//        //4.4关闭连接
//        assert res != null; //断言    如果res为空，则抛出异常   如果res不为空，则执行下面的代码
//        res.close();    //关闭结果集
//        stmt.close();   //关闭Statement对象
//        conn.close();   //关闭Connection对象
//
//    }
//}

package dbutil;

import org.testng.annotations.Test;

import java.sql.*;


public class test2 {
    @Test //测试
    public  void test1() throws Exception {
        //1.加载驱动
//      Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/javademo?characterEncoding=UTF8&autoReconnect=true&useSSL=false",
                "root",
                "lishixue2003");
        System.out.println("连接成功");
        String name = "lsx1";
        String sql = "SELECT * FROM student WHERE name = ?;"; // 查询操作
        //3.获取执行sql语句的对象
//        Statement stmt = conn.createStatement();    //创建Statement对象是干嘛的？    创建Statement对象是为了执行sql语句
        PreparedStatement pstmt = conn.prepareStatement(sql);//创建PreparedStatement对象是为了执行sql语句
        //4.设置参数
        pstmt.setString(1, name);
        ResultSet res = null;
        try {
            //开启事务
            conn.setAutoCommit(false);
            //4.执行sql语句
//            res = stmt.executeQuery(sql);
            res = pstmt.executeQuery();//执行查询操作
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
        res.close();
        pstmt.close();
        conn.close();

    }
    @Test //测试
    public  void test2() throws Exception {
        //1.加载驱动
//      Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/railway?characterEncoding=UTF8&autoReconnect=true&useSSL=false&useServerPrepStmts=true",
                "root",
                "lishixue2003");
        System.out.println("连接成功");
        String name = "lsx1";
        String sql = "select trainno,traintypeno,trainstart from train where traintypeno >= 0 and trainstart like '上海%' and timestart like '2022%' order by timestart asc;"; // 查询操作
        //3.获取执行sql语句的对象
//        Statement stmt = conn.createStatement();    //创建Statement对象是干嘛的？    创建Statement对象是为了执行sql语句
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //4.设置参数
//        pstmt.setString(1, name);
        ResultSet res = null;
        try {
            //开启事务
            conn.setAutoCommit(false);
            //4.执行sql语句
//            res = stmt.executeQuery(sql);
            res = pstmt.executeQuery();//执行查询操作
            //5.处理结果
            while (res.next()) {  //next()方法是否还有下一条数据
//                System.out.println(res.getString("id") + " " + res.getString("name") + " " + res.getString("avatar"));
                System.out.println(res.getObject(1)+" "+res.getObject(2)+" "+res.getObject(3));
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
        assert res != null;
        res.close();
        pstmt.close();
        conn.close();

    }
}

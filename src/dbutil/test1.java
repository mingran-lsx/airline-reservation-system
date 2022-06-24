package dbutil;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test1 {
    //
    @Test
    public static String test2() throws Exception {
        String handleResult = "";
        //1.加载驱动
//      Class.forName("com.mysql.jdbc.Driver");
        //2.获取连接
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/railway?characterEncoding=UTF8&autoReconnect=true&useSSL=false",
                "root",
                "lishixue2003");
        System.out.println("连接成功");
        String sql = "select idtypeno from idtypeinfo where idtype='护照'";
        //3.获取执行sql语句的对象
        Statement stmt = conn.createStatement();    //创建Statement对象是干嘛的？    创建Statement对象是为了执行sql语句

        ResultSet res = null;
        try {
            //开启事务
            conn.setAutoCommit(false);
            //4.执行sql语句
            res = stmt.executeQuery(sql);
            //5.处理结果

            while (res.next()) {  //next()方法是否还有下一条数据
//                System.out.println(res.getObject(1).toString());//getObject(1)是从结果集中获取第一列的数据
                handleResult = res.getObject(1).toString();//getObject(1)是第一列
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
        assert res != null; //断言    如果res为空，则抛出异常   如果res不为空，则执行下面的代码
        res.close();    //关闭结果集
        stmt.close();   //关闭Statement对象
        conn.close();   //关闭Connection对象
        return handleResult;
    }
}

class Main {
    public static void main(String[] args) throws Exception {
        System.out.println(test1.test2());
    }
}
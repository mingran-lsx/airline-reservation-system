package dbutil;

import java.sql.*;

public class SQLHelper {
    public static String driver = "com.mysql.jdbc.Driver";
    public static String url = "jdbc:mysql://127.0.0.1:3306/railway?characterEncoding=UTF8&autoReconnect=true&useSSL=false";
    public static String user = "root", pwd = "lishixue2003";
    public static Connection conn = null;

    static {
        try {
            Class.forName(driver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pwd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    //	/* 执行select语句，返回唯一值 */
//	public static Object executeSingleQuery(String singleSql) {
//		Object r=null;
//		ResultSet res=null;
//		try {
//			Connection conn=DriverManager.getConnection(url, user, pwd);
//			PreparedStatement pre=conn.prepareStatement(singleSql);
//			res=pre.executeQuery();
//			System.out.println(res.next());
//			System.out.println(res.next());
//			System.out.println(res.next());
////			if (rs.next()){
////				System.out.println(rs.getString(1));
////				r=rs.getObject(1);
////				System.out.println(r.toString());
////			}
//			while (res.next()) {  //next()方法是否还有下一条数据
//				System.out.println(res.getString("idtypeno") );
//			}
//			conn.close();
//		} catch(Exception ex) {
//			System.out.println(ex);
//			ex.printStackTrace();
//		}
//		return r;
//	}
    public static String executeSingleQuery(String singleSql) {
//	Object r=null;
//	ResultSet rs=null;
        String handleResult = null;
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;


        try {
            conn = DriverManager.getConnection(url, user, pwd);
            //3.获取执行sql语句的对象
            stmt = conn.createStatement();    //创建Statement对象是干嘛的？    创建Statement对象是为了执行sql语句
            //4.执行sql语句
            res = stmt.executeQuery(singleSql);
            //5.处理结果
            while (res.next()) {  //next()方法是否还有下一条数据
//                System.out.println(res.getObject(1).toString());//getObject(1)是从结果集中获取第一列的数据
                handleResult = res.getObject(1).toString();//getObject(1)是第一列
            }
            res.close();    //关闭结果集
            stmt.close();   //关闭Statement对象
            conn.close();   //关闭Connection对象
        } catch (Exception e) {
            //事务的回滚是指程序或数据处理错误，将程序或数据恢复到上一次正确状态的行为
            e.printStackTrace();  //打印异常信息
        }
        return handleResult;
    }

    public static void closeConnection() {
        try {
            if (conn != null && !conn.isClosed())
                conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /* 执行select语句，返回结果集 */
    public static ResultSet executeQuery(String sql) {//sql语句   返回结果集
        PreparedStatement pstmt = null;
        ResultSet handleResult = null;
        //4.设置参数
        ResultSet res = null;
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(sql);
            //4.执行sql语句
            res = pstmt.executeQuery();//执行查询操作
             handleResult = res;
            //提交事务
//            res.close();
//            pstmt.close();
//            conn.close();
//            printResultSet(res);
        } catch (Exception e) {
            e.printStackTrace();  //打印异常信息
        }
        return handleResult;
    }

    public static int executeUpdate(String sql) {
        int r = 0;
        try {
            Connection conn = DriverManager.getConnection(url, user, pwd);
            PreparedStatement pre = conn.prepareStatement(sql);
            r = pre.executeUpdate();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return r;
    }

    //该方法为事务处理设计，方法中设计了一个参数conn
    public static int executeUpdateForTrans(Connection conn, String sql) throws SQLException {
        int r = 0;
        PreparedStatement pre = conn.prepareStatement(sql);
        r = pre.executeUpdate();
        return r;
    }

}
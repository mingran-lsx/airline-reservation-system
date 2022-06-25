package utils;

import dbutil.SQLHelper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Format {
    static ResultSetMetaData rsmd = null;
    static int columnsNumber = 0;

    public static int getIntByString(String mysql) {
        int i = 0;
        try {
            i = Integer.parseInt(SQLHelper.executeSingleQuery(mysql));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }

    //    格式化输出ResultSet结果集工具类Format
    public static void printResultSet(ResultSet rs) throws SQLException {
        rsmd = rs.getMetaData();//获取结果集的元数据对象
        columnsNumber = rsmd.getColumnCount();//获取结果集的列数
//        while (rs.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
            if (i > 1) System.out.print(",  ");
            String columnValue = rs.getString(i);
            System.out.print(rsmd.getColumnName(i) + ":" + columnValue);
        }
        System.out.println("");
//        }
    }
}

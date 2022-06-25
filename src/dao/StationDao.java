package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbutil.SQLHelper;
import entity.Price;

public class StationDao {
    // 获得站点all
    public static Object[][] stationQuery() {
        Object[][] station = null;
        String mysql;
        try {
            mysql = "select * from station";
            ResultSet rs = SQLHelper.executeQuery(mysql);
            int num = 0;
            while (rs.next()) {
                num++;
            }
            if (num != 0) {
                station = new Object[num][6];

                rs.beforeFirst();

                int i = 0;
                while (rs.next()) {
                    station[i][0] = rs.getString(1);
                    station[i][1] = rs.getString(2);
                    station[i][2] = rs.getString(3);
                    station[i][3] = PlaneDao.timeChange(rs.getTimestamp(4));
                    station[i][4] = PlaneDao.timeChange(rs.getTimestamp(5));
                    station[i][5] = rs.getString(6);
                    i++;
                }
            }
        } catch (Exception ee) {
            System.out.println(ee);
        }
        return station;
    }

    // 查询航班、站点信息得到站点序号
    public static String stationnoQuery(String trainno, String stationinfo) {
        String stationno = null;
        String mysql;
        try {
            mysql = "select stationno from station where trainno='" + trainno + "' and stationinfo='" + stationinfo
                    + "'";
            ResultSet rs = SQLHelper.executeQuery(mysql);
            if (rs.next()) {
                stationno = rs.getString(1);
            }
        } catch (Exception ee) {
            System.out.println(ee);
        }
        return stationno;
    }

    // 查询航班、起点、站点名获得这三个及timestart timereach
    public static Object[][] planeno_stationQuery(String trainno, String trainstart, String stationinfo) {
        Object[][] station = null;
        String mysql, mysqlwhere;

        try {
            //  查询航班、起点、站点名获得这三个及timestart timereach
            mysql = "select trainno,stationno,stationinfo,timestart,timereach,stationmile from station";
            mysqlwhere = " where trainno='" + trainno + "' and stationinfo='" + stationinfo
                    + "' order by timereach asc,timestart asc";
            mysql = mysql + mysqlwhere;

            ResultSet rs = SQLHelper.executeQuery(mysql);
            int num = 0;
            while (rs.next()) {
                num++;
            }
            if (num != 0) {
                station = new Object[num][6];

                rs.beforeFirst();
                int i = 0;
                while (rs.next()) {
                    station[i][0] = rs.getString(1);
                    station[i][1] = rs.getString(2);
                    station[i][2] = rs.getString(3);
                    station[i][3] = rs.getString(4);
                    station[i][4] = rs.getString(5);
                    station[i][5] = rs.getString(6);
                    i++;
                }
            }
            SQLHelper.closeConnection();
        } catch (SQLException ee) {
            System.out.println(ee);
        }
        return station;
    }

    // 插入站点
    public static int stationInsert(String trainno, String stationno, String stationinfo, String timereach,
                                    String timestart, String stationmile) {
        int k = 0;
        String mysql;
        try {
            mysql = "insert into station values('" + trainno + "','" + stationno + "','" + stationinfo + "','"
                    + timereach + "','" + timestart + "','" + stationmile + "')";
            k = SQLHelper.executeUpdate(mysql);
            SQLHelper.closeConnection();
        } catch (Exception ee) {
            System.out.println(ee);
        }
        return k;

    }

    // 更新站点
    public static int stationUpdate(String stationno, String stationinfo, String timereach, String timestart,
                                    String stationmile) {
        int k = 0;
        String mysql;
        try {
            mysql = "update station set stationinfo='" + stationinfo + "',timereach='" + timereach + "',timestart='"
                    + timestart + "',stationmile='" + stationmile + "' where stationno='" + stationno + "'";
            k = SQLHelper.executeUpdate(mysql);
            SQLHelper.closeConnection();
        } catch (Exception ee) {
            System.out.println(ee);
        }
        return k;
    }

    // 删除站点
    public static int stationDelete(String stationno) {
        int k = 0;
        String mysql;
        try {
            mysql = "delete from station where stationno='" + stationno + "'";
            k = SQLHelper.executeUpdate(mysql);
            SQLHelper.closeConnection();
        } catch (Exception ee) {
            System.out.println(ee);
        }
        return k;
    }

    // 用户查找符合条件的站点并返回数组
    public static Object[][] stationinfoQuery(String trainstart, String trainend, String time, int type) {
        Object[][] rows = null;
        String mysql_trainno, mysql_station;
//        System.out.println(trainstart + " " + trainend + " " + time + " " + type);
        try {
            mysql_trainno = "select trainno,traintypeno,trainstart from train where traintypeno >= " + type
                    + " and trainstart like '" + trainstart + "%' and timestart like '" + time
                    + "%' order by timestart asc";
            ResultSet rs_trainno = SQLHelper.executeQuery(mysql_trainno);
//            System.out.println(mysql_trainno+"resultset");
            int num = 0;//记录符合条件的航班数
            while (rs_trainno.next()) {//遍历符合条件的航班
                System.out.println(rs_trainno.getObject(1)+ "StationDao.java 161");
                mysql_station = "select trainno,stationinfo,timestart,timereach,stationmile from station where trainno='"
                        + rs_trainno.getString(1) + "' and stationinfo like '" + trainend + "%' and timestart like '"
                        + time + "%' order by timestart asc";
//                System.out.println(mysql_station);
                ResultSet rs_station = SQLHelper.executeQuery(mysql_station);//查询符合条件的站点

                while (rs_station.next()) {
                    System.out.println(rs_station.getObject(1)+ "StationDao.java 169");
                    num++;
                }
            }
            if (num != 0) {
                rows = new Object[num][10];
                rs_trainno.beforeFirst();// 将游标移到第一行    以便从第一行开始遍历
                int i = 0; // 记录行数
                while (rs_trainno.next()) {
                    mysql_station = "select trainno,stationinfo,timestart,timereach,stationmile from station where trainno='"
                            + rs_trainno.getString(1) + "' and stationinfo like '" + trainend
                            + "%' and timestart like '" + time + "%' order by timestart asc";
                    ResultSet rs_station = SQLHelper.executeQuery(mysql_station);// 查询符合条件的站点
                    while (rs_station.next()) {
                        rows[i][0] = rs_station.getString(1);
                        rows[i][1] = PlaneDao.traintypeQuery(rs_trainno.getInt(2));
                        rows[i][2] = rs_trainno.getString(3);
                        rows[i][3] = rs_station.getString(2);
                        rows[i][4] = PlaneDao.timeChange(rs_station.getTimestamp(3));
                        rows[i][5] = PlaneDao.timeChange(rs_station.getTimestamp(4));
                        rows[i][6] = rs_station.getString(5);

                        ArrayList<Price> pr = PriceDao.priceQuery(rs_station.getString(1), rs_station.getString(2));
                        rows[i][7] = String.valueOf(pr.get(0).getPricehigh());
                        rows[i][8] = String.valueOf(pr.get(0).getPriceone());
                        rows[i][9] = String.valueOf(pr.get(0).getPricetwo());
                        i++;
                    }
                }
            }
            SQLHelper.closeConnection();
        } catch (SQLException ee) {
            System.out.println(ee);
        }
        return rows;
    }
}

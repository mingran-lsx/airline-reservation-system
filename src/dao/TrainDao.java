package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import dbutil.SQLHelper;
import entity.Train;
import types.TrainAndTicketType;
import utils.Fun;

public class TrainDao {
    // 转换时间格式
    public static String timeChange(Timestamp date) {
        String temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        return temp;
    }

    // 查找飞机类型
    public static String traintypeQuery(int traintypeno) {
        String airplanetype = null;
        String mysql = "select airplanetype from airplanetypeinfo where airplanetypeno = " + traintypeno;
        airplanetype = (String) SQLHelper.executeSingleQuery(mysql);
        return airplanetype;
    }

    // 查找飞机类型序号
    public static int traintypenoQuery(String traintype) {
        int airplanetypeno = 0;
        String mysql = "select airplanetypeno from airplanetypeinfo where airplanetype='" + traintype + "'";
        airplanetypeno = Fun.getIntByString(mysql);
        return airplanetypeno;
    }

    // 显示路线（路线管理）
    public static Object[][] traininfo() {
        Object[][] rows = null;
        ArrayList<Train> trainList = new ArrayList<Train>();
        String mysql;

        try {
            mysql = "select * from train order by timestart asc,timereach asc";

            ResultSet rs = SQLHelper.executeQuery(mysql);

            int num = 0;
            while (rs.next()) {
                num++;
            }
            rows = new Object[num][7];

            rs.beforeFirst();
            while (rs.next()) {
                Train tr = new Train();
                tr.setTrainno(rs.getString(1));
                tr.setTraintypeno(rs.getInt(2));
                tr.setTrainstart(rs.getString(3));
                tr.setTrainend(rs.getString(4));
                tr.setTimestart(rs.getTimestamp(5));
                tr.setTimereach(rs.getTimestamp(6));
                tr.setTimemove(rs.getString(7));
                trainList.add(tr);
            }

            for (int i = 0; i < num; i++) {
                rows[i][0] = trainList.get(i).getTrainno();
                rows[i][1] = traintypeQuery(trainList.get(i).getTraintypeno());
                rows[i][2] = trainList.get(i).getTrainstart();
                rows[i][3] = trainList.get(i).getTrainend();
                rows[i][4] = timeChange(trainList.get(i).getTimestart());
                rows[i][5] = timeChange(trainList.get(i).getTimereach());
                rows[i][6] = trainList.get(i).getTimemove();
            }
            SQLHelper.closeConnection();
        } catch (SQLException ee) {
            System.out.println(ee);
        }
        return rows;
    }

    // 录入航班
    public static int trainInsert(String trainno, int traintypeno, String trainstart, String trainend, String timestart, String timereach, String timemove, int ticket) {
        int k1 = 0, k2 = 0, k3 = 0;
        String mysql_train, mysql_ticket, mysql_sale;
        try {
            mysql_train = "insert into train values('" + trainno + "'," + traintypeno + ",'" + trainstart + "','" + trainend + "','" + timestart + "','" + timereach + "','" + timemove + "')";
            mysql_ticket = "insert into ticket values('" + trainno + "'," + ticket + ")";
            mysql_sale = "insert into sale values('" + trainno + "',0)";

            k1 = SQLHelper.executeUpdate(mysql_train);
            k2 = SQLHelper.executeUpdate(mysql_ticket);
            k3 = SQLHelper.executeUpdate(mysql_sale);

            SQLHelper.closeConnection();
        } catch (Exception ee) {
            System.out.println(ee);
        }
        return k1 + k2 + k3;
    }

    // 路线更新
    public static int trainUpdate(String trainno, String traintype, String trainstart, String trainend, String timestart, String timereach, String timemove) {
        int traintypeno = traintypenoQuery(traintype);

        int k = 0;
        String mysql;
        try {
            mysql = "update train set traintypeno=" + traintypeno + ",trainstart='" + trainstart + "',trainend='" + trainend + "',timestart='" + timestart + "',timereach='" + timereach + "',timemove='" + timemove + "' where trainno='" + trainno + "'";
            k = SQLHelper.executeUpdate(mysql);
            SQLHelper.closeConnection();
        } catch (Exception ee) {
            System.out.println(ee);
        }
        return k;
    }

    // 删除航班
    public static int trainDelete(String trainno) {
        int k1 = 0, k2 = 0;
        String mysql, mysql_sale;
        try {
            mysql = "delete from train where trainno='" + trainno + "'";
            mysql_sale = "delete from sale where trainno='" + trainno + "'";

            k1 = SQLHelper.executeUpdate(mysql);
            k2 = SQLHelper.executeUpdate(mysql_sale);

            SQLHelper.closeConnection();
        } catch (Exception ee) {
            System.out.println(ee);
        }
        return k1 + k2;
    }

    // 查找日期获取航班基本信息 查找余票数量 并且查看在售状态
    public static Object[][] train_ticketQuery(String timestart) {
        Object[][] rows = null;
        String mysql_train, mysql_ticket, mysql_sale;
        ArrayList<TrainAndTicketType> trainAndTicketTypeList = new ArrayList<TrainAndTicketType>();

        try {
            mysql_train = "select * from train where timestart like '" + timestart + "%'";
//			mysql_ticket = "select * from train, tickettype where train.trainno = tickettype.trainno and train.trainno = 'G102';";
            ResultSet rs = SQLHelper.executeQuery(mysql_train);

            int num = 0;
            while (rs.next()) {
                num++;
            }
            if (num != 0) {
                rows = new Object[num][15];

                rs.beforeFirst();// 将指针移到第一行    即第一条记录  如果没有记录则指针不会移动   如果有记录则指针会移动
                int i = 0;
                while (rs.next()) {
                    rows[i][0] = rs.getString(1);
                    rows[i][1] = traintypeQuery(rs.getInt(2));
                    rows[i][2] = rs.getString(3);
                    rows[i][3] = rs.getString(4);
                    rows[i][4] = timeChange(rs.getTimestamp(5));
                    rows[i][5] = timeChange(rs.getTimestamp(6));
                    rows[i][6] = rs.getString(7);
//                    rows[i][7] = rs.getFloat(8);
//                    rows[i][8] = rs.getFloat(9);//积分
//                    rows[i][9] = rs.getFloat(10);//
                    rows[i][7] = rs.getObject(8);
                    rows[i][8] = rs.getObject(9);//积分
                    rows[i][9] = rs.getObject(10);//
                    mysql_ticket = "select tickethigh ,ticketone,tickettwo from tickettype where trainno='" + rs.getString(1) + "'";
                    ResultSet rs_ticket = SQLHelper.executeQuery(mysql_ticket);
                    rs_ticket.next();// .next()方法是指针移到下一条记录
                    rows[i][10] = rs_ticket.getString(1);//商务座
                    rows[i][11] = rs_ticket.getString(2);//公务舱
                    rows[i][12] = rs_ticket.getString(3);//经济舱
                    mysql_sale = "select saletypeno from sale where trainno='" + rs.getString(1) + "'";
                    ResultSet rs_sale = SQLHelper.executeQuery(mysql_sale);
                    rs_sale.next();

//                    rows[i][11] = saletypeQuery(rs_sale.getInt(1));


                    trainAndTicketTypeList.add(new TrainAndTicketType(rs.getString(1),
                            rs.getInt(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getTimestamp(5),
                            rs.getTimestamp(6),
                            rs.getString(7),
                            rs.getFloat(8),
                            rs.getInt(9),
                            rs_ticket.getInt(1),
                            rs_ticket.getInt(2),
                            rs_ticket.getInt(3)
                            ));

//                    mysql_ticket = "select ticketno from ticket where trainno='" + rs.getString(1) + "'";
//                    ResultSet rs_ticket = SQLHelper.executeQuery(mysql_ticket);
//                    rs_ticket.next();
//                    rows[i][7] = rs_ticket.getString(1);
//
//                    mysql_sale = "select saletypeno from sale where trainno='" + rs.getString(1) + "'";
//                    ResultSet rs_sale = SQLHelper.executeQuery(mysql_sale);
//                    rs_sale.next();
//                    rows[i][8] = saletypeQuery(rs_sale.getInt(1));

                    i++;
                }
                System.out.println(trainAndTicketTypeList);
                System.out.println(Arrays.deepToString(rows));

            }
            SQLHelper.closeConnection();
        } catch (SQLException ee) {
            System.out.println(ee);
        }
        return rows;
    }

    // 开放售票
    public static boolean train_ticketOnsale(String timestart) {
        String mysql_train, mysql_sale;
        int k = 0, num = 0;

        try {
            mysql_train = "select trainno from train where timestart like '" + timestart + "%'";
            ResultSet rs = SQLHelper.executeQuery(mysql_train);
            while (rs.next()) {
                mysql_sale = "update sale set saletypeno=" + 1 + " where trainno='" + rs.getString(1) + "'";
                k = k + SQLHelper.executeUpdate(mysql_sale);
                num++;
            }
            SQLHelper.closeConnection();
        } catch (SQLException ee) {
            System.out.println(ee);
        }
        return k == num;
    }

    // 停止售票
    public static int train_ticketStopsale(String timestart) {
        String mysql_train, mysql_temp, mysql_sale;
        int k = 0, num = 0, stop = 0;

        try {
            mysql_train = "select trainno from train where timestart like '" + timestart + "%'";
            ResultSet rs = SQLHelper.executeQuery(mysql_train);
            while (rs.next()) {
                mysql_temp = "select saletypeno from sale where trainno ='" + rs.getString(1) + "'";
                ResultSet rs_temp = SQLHelper.executeQuery(mysql_temp);
                while (rs_temp.next()) {
                    if (rs_temp.getInt(1) == 0) {
                        return stop;
                    } else {
                        mysql_sale = "update sale set saletypeno=" + 2 + " where trainno='" + rs.getString(1) + "'";
                        k = k + SQLHelper.executeUpdate(mysql_sale);
                        num++;
                    }
                }
            }
            SQLHelper.closeConnection();
        } catch (SQLException ee) {
            System.out.println(ee);
        }
        if (k == num) {
            stop = 1;
        }
        return stop;
    }

    // saletypeno转换
    public static String saletypeQuery(int saletypeno) {
        String mysql = "select saletype from saletypeinfo where saletypeno=" + saletypeno;
        String saletype = (String) SQLHelper.executeSingleQuery(mysql);
        return saletype;
    }

    // saletype转换
    public static int saletypenoQuery(String saletype) {
        String mysql = "select saletypeno from saletypeinfo where saletype='" + saletype + "'";
        int saletypeno = Fun.getIntByString(mysql);
        return saletypeno;
    }

}

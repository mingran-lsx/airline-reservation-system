package dao;

import java.sql.*;

import dbutil.SQLHelper;

public class SeatDao {
	//座位信息查询
	public static Object[][] seathighQuery(String trainno) {
		Object[][] seat=null;
		String mysql;
		try {
			//
			mysql="select seattrain,seatno,seattype from seat where ( seattype='头等舱' )  and seattrain like '"+"%"+trainno+"%' and seattype= 0";//
			ResultSet rs=SQLHelper.executeQuery(mysql);
			
			int num=0;
			while(rs.next()) {
				System.out.println(rs.getString("seattrain")+"seatDao-19");
				num++;
			}
			
			seat=new Object[num][3];
			rs.beforeFirst();
			int i=0;
			while(rs.next()) {
				seat[i][0]=rs.getString(1);
				seat[i][1]=rs.getInt(2);
				seat[i][2]=rs.getString(3);
				i++;
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return seat;
	}
	public static Object[][] seatoneQuery(String trainno) {
		Object[][] seat=null;
		String mysql;
		try {
			mysql="select seattrain,seatno,seattype from seat where ( seattype='公务舱'  )  and seattrain like '"+"%"+trainno+"%' and seattype= 0";
			ResultSet rs=SQLHelper.executeQuery(mysql);
			int num=0;
			while(rs.next()) {
				num++;
			}
			seat=new Object[num][3];
			rs.beforeFirst();
			int i=0;
			while(rs.next()) {
				seat[i][0]=rs.getString(1);
				seat[i][1]=rs.getInt(2);
				seat[i][2]=rs.getString(3);
				i++;
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return seat;
	}
	public static Object[][] seattwoQuery(String trainno) {
		Object[][] seat=null;
		String mysql;
		try {
			mysql="select seattrain,seatno,seattype from seat where ( seattype='经济舱')  and seattrain like '"+"%"+trainno+"%' and seattype= 0";
			ResultSet rs=SQLHelper.executeQuery(mysql);
			int num=0;
			while(rs.next()) {
				num++;
			}
			seat=new Object[num][3];
			rs.beforeFirst();
			int i=0;
			while(rs.next()) {
				seat[i][0]=rs.getString(1);
				seat[i][1]=rs.getInt(2);
				seat[i][2]=rs.getString(3);
				i++;
			}
		}catch(SQLException e) {
			System.out.println(e);
		}
		return seat;
	}
	
	//座位（0->1）
	public static int seatreduceUpdate(String seattrain,int seatno) {
		int k;
		String sql_update;
		sql_update="update seat set seatstate = 1 where seattrain='"+seattrain+"' and seatno="+seatno;
		k=SQLHelper.executeUpdate(sql_update);
		return k;
	}
	
	//座位（1->0）
	public static int seataddUpdate(String seattrain,int seatno) {
		int k;
		String sql_update;
		sql_update="update seat set seatstate = 0 where seattrain='"+seattrain+"' and seatno="+seatno;
		k=SQLHelper.executeUpdate(sql_update);
		return k;
	}
	
	
	
	
}

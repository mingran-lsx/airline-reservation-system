package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbutil.SQLHelper;
import entity.Price;

public class PriceDao {

	// 票价获取
	public static ArrayList<Price> priceQuery(String trainno, String stationinfo) {
		ArrayList<Price> priceList = new ArrayList<Price>();
		String mysql, mysqlwhere;
		try {
			mysql = "select pricehigh,priceone,pricetwo from price,station";
			mysqlwhere = " where station.stationno=price.stationno and station.trainno='" + trainno
					+ "' and station.stationinfo='" + stationinfo + "'";
			mysql = mysql + mysqlwhere;
			ResultSet rs = SQLHelper.executeQuery(mysql);
			while (rs.next()) {
				Price pri = new Price();
				pri.setPricehigh(rs.getInt(1));
				pri.setPriceone(rs.getInt(2));
				pri.setPricetwo(rs.getInt(3));
				priceList.add(pri);
			}
			SQLHelper.closeConnection();
		} catch (SQLException ee) {
			System.out.println(ee);
		}
		System.out.println(priceList.toString()+"PriceDao.java 33");
		return priceList;
	}
	
	//票价修改
	public static int priceUpdate(int high,int one,int two,String stationno) {
		int k=0;
		String mysql;
		try {
			mysql = "update price set pricehigh="+high+",priceone="+one+",pricetwo="+two+" where stationno='"+stationno+"'";
			k = SQLHelper.executeUpdate(mysql);
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return k;
	}
	
	//票价删除
	public static int priceDelete(String stationno) {
		int k=0;
		String mysql;
		try {
			mysql="delete from price where stationno='"+stationno+"'";
			k = SQLHelper.executeUpdate(mysql);
			SQLHelper.closeConnection();
		}catch (Exception ee) {
			System.out.println(ee);
		}
		return k;
	}
	
	//票价录入
	public static int priceInsert(String stationno,int high,int one,int two) {
		int k = 0;
		String mysql;
		try {
			mysql= "insert into price values('"+stationno+"',"+high+","+one+","+two+")";
			k = SQLHelper.executeUpdate(mysql);
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return k;
		
	}
}

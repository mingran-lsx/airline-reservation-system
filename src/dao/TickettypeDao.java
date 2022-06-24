package dao;

import dbutil.SQLHelper;

import static utils.Fun.getIntByString;

public class TickettypeDao {
	//String转int的方法
	// 头等舱查询
	public static int tickethighQuery(String trainno) {
		int num = 0;
		String mysql;
		mysql = "select tickethigh from tickettype where trainno='" + trainno + "'";
//		num = (int) SQLHelper.executeSingleQuery(mysql);
		num = getIntByString(mysql);
		return num;
	}

	// 公务舱查询
	public static int ticketoneQuery(String trainno) {
		int num = 0;
		String mysql;
		mysql = "select ticketone from tickettype where trainno='" + trainno + "'";
		num = getIntByString(mysql);
//		num = (int) SQLHelper.executeSingleQuery(mysql);
		return num;
	}

	// 经济舱查询
	public static int tickettwoQuery(String trainno) {
		int num = 0;
		String mysql;
		mysql = "select tickettwo from tickettype where trainno='" + trainno + "'";
		num = getIntByString(mysql);
		return num;
	}

	// 购买头等舱*1
	public static int tickethighReduce(String trainno) {
		int k = 0;
		int num = TickettypeDao.tickethighQuery(trainno) - 1;
		String mysql;
		if (num >= 0) {
			mysql = "update tickettype set tickethigh=" + num + " where trainno='" + trainno + "'";
			k = SQLHelper.executeUpdate(mysql);
		}
		return k;
	}

	// 购买公务舱*1
	public static int ticketoneReduce(String trainno) {
		int k = 0;
		int num = TickettypeDao.ticketoneQuery(trainno) - 1;
		String mysql;
		if (num >= 0) {
			mysql = "update tickettype set ticketone=" + num + " where trainno='" + trainno + "'";
			k = SQLHelper.executeUpdate(mysql);
		}
		return k;
	}

	// 购买经济舱*1
	public static int tickettwoReduce(String trainno) {
		int k = 0;
		int num = TickettypeDao.tickettwoQuery(trainno) - 1;
		String mysql;
		if (num >= 0) {
			mysql = "update tickettype set tickettwo=" + num + " where trainno='" + trainno + "'";
			k = SQLHelper.executeUpdate(mysql);
		}
		return k;
	}

	// 退票头等舱
	public static int tickethighAdd(String trainno) {
		int k = 0;
		int num = TickettypeDao.tickethighQuery(trainno) + 1;
		String mysql;
		mysql = "update tickettype set tickethigh=" + num + " where trainno='" + trainno + "'";
		k = SQLHelper.executeUpdate(mysql);
		return k;
	}

	// 退票公务舱
	public static int ticketoneAdd(String trainno) {
		int k = 0;
		int num = TickettypeDao.ticketoneQuery(trainno) + 1;
		String mysql;
		mysql = "update tickettype set ticketone=" + num + " where trainno='" + trainno + "'";
		k = SQLHelper.executeUpdate(mysql);
		return k;
	}

	// 退票经济舱
	public static int tickettwoAdd(String trainno) {
		int k = 0;
		int num = TickettypeDao.tickettwoQuery(trainno) + 1;
		String mysql;
		mysql = "update tickettype set tickettwo=" + num + " where trainno='" + trainno + "'";
		k = SQLHelper.executeUpdate(mysql);
		return k;
	}
}

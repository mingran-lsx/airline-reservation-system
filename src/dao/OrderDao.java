package dao;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import dbutil.SQLHelper;
import entity.Order;
import entity.Price;

public class OrderDao {
	// usertel生成订单
	// 参数：usertel,trainno,trainstart,trainend,timestart,timereach,timemove,seattype,seatno,price
	public static int orderInsert(String usertel, String pername, Object[] train, String seat, String buydate) {
		int k1 = 0, k2 = 0;
		//
		System.out.println(usertel + " " + pername + " " + Arrays.toString(train) + " " + seat + " " + buydate+"OrderDao.java 17");
		Object[] person = UserDao.userpersonQuery(usertel, pername);
		String pertel = String.valueOf(person[1]);
		String perid = String.valueOf(person[3]);
		String trainno = String.valueOf(train[0]);
		String trainstart = String.valueOf(train[1]);
		String stationinfo = String.valueOf(train[2]);
		String timestart = String.valueOf(train[3]);
		String timereach = String.valueOf(train[4]);
		System.out.println(trainno + " " + trainstart + " " + stationinfo + " " + timestart + " " + timereach+"OrderDao.java 27");
		Object[][] seatinfo = null;//Object[][] 是一个二维数组

		int price = 0;
		ArrayList<Price> pri = PriceDao.priceQuery(trainno, stationinfo);
		if (seat.equals("头等舱")) {
			price = pri.get(0).getPricehigh();
			seatinfo = SeatDao.seathighQuery(trainno);
		} else if (seat.equals("公务舱")) {
			price = pri.get(0).getPriceone();
			seatinfo = SeatDao.seatoneQuery(trainno);

		} else if (seat.equals("经济舱")) {
			price = pri.get(0).getPricetwo();
			seatinfo = SeatDao.seattwoQuery(trainno);
		}

//		System.out.println(Arrays.deepToString(seatinfo));
		assert seatinfo != null;
		System.out.println(Arrays.deepToString(seatinfo)+"OrderDao.java 44");
		String seattrain = "";
		int seatno =-1;
		String seattype = null;
		String mysql;

		try {
			seattrain =String.valueOf(seatinfo[0][0]);
			seatno= Integer.parseInt(String.valueOf(seatinfo[0][1]));
			seattype = String.valueOf(seatinfo[0][2]);
			////////////////
			mysql = "insert into `order` values('" + usertel + "','" + pertel + "','" + pername + "','" + perid + "','"
					+ trainno + "','" + trainstart + "','" + stationinfo + "','" + timestart + "','" + timereach + "','"
					+ seattrain + "'," + seatno + ",'" + seattype + "'," + price + ",'" + buydate + "')";
			k1 = SQLHelper.executeUpdate(mysql);
			k2 = SeatDao.seatreduceUpdate(seattrain, seatno);
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return k1 + k2;
	}

	// 查询所有订单 不显示usertel
	public static Object[][] orderQuery() {
		Object[][] order = null;
		ArrayList<Order> orderList = new ArrayList<Order>();
		String mysql;

		try {
			mysql = "select pertel,pername, perid,trainno, trainstart,stationinfo,timestart,timereach, seattrain,seatno,seattype,price,buydate from `order`";
			ResultSet rs = SQLHelper.executeQuery(mysql);

			int num = 0;
			while (rs.next()) {
				num++;
			}
			order = new Object[num][14];

			rs.beforeFirst();
			while (rs.next()) {
				Order or = new Order();
				or.setPertel(rs.getString(1));
				or.setPername(rs.getString(2));
				or.setPerid(rs.getString(3));
				or.setTrainno(rs.getString(4));
				or.setTrainstart(rs.getString(5));
				or.setStationinfo(rs.getString(6));
				or.setTimestart(rs.getString(7));
				or.setTimereach(rs.getString(8));
				or.setSeattrain(rs.getString(9));
				or.setSeatno(rs.getInt(10));
				or.setSeattype(rs.getString(11));
				or.setPrice(rs.getInt(12));
				or.setBuydate(rs.getTimestamp(13));
				orderList.add(or);
			}

			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				order[i][0] = orderList.get(i).getPertel();
				order[i][1] = orderList.get(i).getPername();
				order[i][2] = orderList.get(i).getPerid();
				order[i][3] = orderList.get(i).getTrainno();
				order[i][4] = orderList.get(i).getTrainstart();
				order[i][5] = orderList.get(i).getStationinfo();
				order[i][6] = orderList.get(i).getTimestart();
				order[i][7] = orderList.get(i).getTimereach();
				order[i][8] = orderList.get(i).getSeattrain();
				order[i][9] = orderList.get(i).getSeatno();
				order[i][10] = orderList.get(i).getSeattype();
				order[i][11] = orderList.get(i).getPrice();
				order[i][12] = TrainDao.timeChange(orderList.get(i).getBuydate());
				i++;
			}
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return order;
	}

	// 用户订单显示、手机号查询订单
	public static Object[][] order_telQuery(String usertel) {
		Object[][] order = null;
		ArrayList<Order> orderList = new ArrayList<Order>();
		String mysql;

		try {
			mysql = "select pertel,pername, perid,trainno, trainstart,stationinfo,timestart,timereach, seattrain,seatno,seattype,price,buydate from `order` where usertel='"
					+ usertel + "'";

			ResultSet rs = SQLHelper.executeQuery(mysql);

			int num = 0;
			while (rs.next()) {
				System.out.println(rs.getObject(1)+"OrderDao.java 141" );
				num++;
			}
			order = new Object[num][14];

			rs.beforeFirst();
			while (rs.next()) {
				Order or = new Order();
				or.setPertel(rs.getString(1));
				or.setPername(rs.getString(2));
				or.setPerid(rs.getString(3));
				or.setTrainno(rs.getString(4));
				or.setTrainstart(rs.getString(5));
				or.setStationinfo(rs.getString(6));
				or.setTimestart(rs.getString(7));
				or.setTimereach(rs.getString(8));
				or.setSeattrain(rs.getString(9));
				or.setSeatno(rs.getInt(10));
				or.setSeattype(rs.getString(11));
				or.setPrice(rs.getInt(12));
				or.setBuydate(rs.getTimestamp(13));
				orderList.add(or);
			}

			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				order[i][0] = orderList.get(i).getPertel();
				order[i][1] = orderList.get(i).getPername();
				order[i][2] = orderList.get(i).getPerid();
				order[i][3] = orderList.get(i).getTrainno();
				order[i][4] = orderList.get(i).getTrainstart();
				order[i][5] = orderList.get(i).getStationinfo();
				order[i][6] = orderList.get(i).getTimestart();
				order[i][7] = orderList.get(i).getTimereach();
				order[i][8] = orderList.get(i).getSeattrain();
				order[i][9] = orderList.get(i).getSeatno();
				order[i][10] = orderList.get(i).getSeattype();
				order[i][11] = orderList.get(i).getPrice();
				order[i][12] = TrainDao.timeChange(orderList.get(i).getBuydate());
				i++;
			}
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return order;

	}

	// 查询订单是否重复
	public static int reorderQuery(String pername, String trainno, String timestart) {
		int re = 0;
		String mysql;

		try {
			mysql = "select * from `order` where pername='" + pername + "' and trainno='" + trainno
					+ "' and timestart='" + timestart + "'";
			ResultSet rs = SQLHelper.executeQuery(mysql);
			if (rs.next()) {
				re = 0;
			} else {
				re = 1;
			}
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return re;
	}

	// 订单删除 、返回票数 、重置座位信息
	public static int orderDelete(String pername, String trainno, String timestart, String seat, String seattrain,
			int seatno) {
		int k1 = 0, k2 = 0, k3 = 0;
		String mysql_1;

		try {
			mysql_1 = "delete from `order` where pername='" + pername + "' and trainno='" + trainno
					+ "' and timestart='" + timestart + "'";
			k1 = SQLHelper.executeUpdate(mysql_1);

			if (seat.equals("头等舱") || seat.equals("")) {
				k2 = TickettypeDao.tickethighAdd(trainno);
			} else if (seat.equals("公务舱")) {
				k2 = TickettypeDao.ticketoneAdd(trainno);
			} else if (seat.equals("经济舱")) {
				k2 = TickettypeDao.tickettwoAdd(trainno);
			}

			k3 = SeatDao.seataddUpdate(seattrain, seatno);

			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return k1 + k2 + k3;
	}

}

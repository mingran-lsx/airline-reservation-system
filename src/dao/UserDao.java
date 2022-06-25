package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.User;
import entity.Userperson;
import dbutil.SQLHelper;
import utils.Format;
public class UserDao {
	// 用户登录
	public static int userValidate(String usertel, String userpwd) {
		ArrayList<User> userList = new ArrayList<User>();
		int flag = 0;

		String mysql = "select usertel,userpwd from user";
		String mysql_2 = " where usertel='" + usertel + "'";
		mysql = mysql + mysql_2;

		try {
			ResultSet rs = SQLHelper.executeQuery(mysql);
			System.out.println(rs);
			if (rs.next()) {
				User users = new User();
				users.setUsertel(rs.getString(1));
				users.setUserpwd(rs.getString(2));
				userList.add(users);
			}

			if (userList.isEmpty())
				flag = -1; // 不存在该用户名
			else {
				if (userList.get(0).getUserpwd().equals(userpwd))
					flag = 1; // 密码正确
				else
					flag = 2; // 密码错误
			}

			SQLHelper.closeConnection();
			return flag;

		} catch (SQLException ee) {
			System.out.println(ee);
		}

		return flag;
	}

	// 查找id类型数字
	public static int idtypenoQuery(String idtype) {
		int idtypeno = 0;
		String mysql = "select idtypeno from idtypeinfo where idtype='" + idtype + "'";
		idtypeno = Format.getIntByString(mysql);
//		System.out.println(idtypeno);
		return idtypeno;
	}

	// 查找id类型
	public static String idtypeQuery(int idtypeno) {
		String idtype = null;
		String mysql = "select idtype from idtypeinfo where idtypeno='" + idtypeno + "'";
		idtype = (String) SQLHelper.executeSingleQuery(mysql);
		return idtype;
	}

	// 查找证件类型数字
	public static int usertypenoQuery(String usertype) {
		int usertypeno = 0;
		String mysql = "select usertypeno from usertypeinfo where usertype='" + usertype + "'";
		usertypeno = Format.getIntByString(mysql);
		return usertypeno;
	}

	// 查找证件类型
	public static String usertypeQuery(int usertypeno) {
		String usertype = null;
		String mysql = "select usertype from usertypeinfo where usertypeno=" + usertypeno;
		usertype = (String) SQLHelper.executeSingleQuery(mysql);
		return usertype;
	}

	// 用户注册
	public static int userInsert(String usertel, String userpwd, String user, String idtype, String id,
			String usertype) {
		System.out.println(usertel + " " + user + " " + usertype + " " + idtype + " " + id+"UserDao.java 85");
		int idtypeno = idtypenoQuery(idtype);
		int usertypeno = usertypenoQuery(usertype);

		String mysql = "insert into user values('" + usertel + "','" + userpwd + "','" + user + "'," + idtypeno + ",'"
				+ id + "'," + usertypeno + ")";
		int k = 0;
		try {
			k = SQLHelper.executeUpdate(mysql);
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return k;
	}

	// usertel+查找常用联系人的名字获取乘客类型no
	public static int pertypenoQuery(String usertel, String pername) {
		int pertypeno = 0;
		String mysql;
		try {
			mysql = "select pertypeno from userperson where usertel='" + usertel + "' and pername='" + pername + "'";
			ResultSet rs = SQLHelper.executeQuery(mysql);
			if (rs.next()) {
				pertypeno = rs.getInt(1);
			}
			SQLHelper.closeConnection();
		} catch (SQLException ee) {
			System.out.println(ee);
		}
		return pertypeno;
	}

	// usertel+pername具体联系人查找
	public static Object[] userpersonQuery(String usertel, String pername) {
		Object[] per = null;
		String mysql;
		try {
			mysql = "select usertel,pertel,pername,perid from userperson where usertel='" + usertel + "' and pername='"
					+ pername + "'";
			ResultSet rs = SQLHelper.executeQuery(mysql);

			per = new Object[4];

			rs.beforeFirst();
			int i = 0;
			while (rs.next()) {
				per[0] = rs.getString(1);
				per[1] = rs.getString(2);
				per[2] = rs.getString(3);
				per[3] = rs.getString(4);
				i++;
			}
			SQLHelper.closeConnection();
		} catch (SQLException ee) {
			System.out.println(ee);
		}
		return per;
	}

	// usertel常用联系人查找
	public static Object[][] userpersonQuery(String usertel) {
		Object[][] rows = null;

		ArrayList<Userperson> perList = new ArrayList<Userperson>();

		String mysql;

		try {
			mysql = "select usertel,pertel,pername,idtypeno,perid,pertypeno from userperson where usertel='" + usertel
					+ "'";
			ResultSet rs = SQLHelper.executeQuery(mysql);

			int num = 0;
			while (rs.next()) {
				num++;
			}
			rows = new Object[num][5];

			rs.beforeFirst();
			while (rs.next()) {
				Userperson per = new Userperson();
				per.setUsertel(rs.getString(1));
				per.setPertel(rs.getString(2));
				per.setPername(rs.getString(3));
				per.setIdtypeno(rs.getInt(4));
				per.setPerid(rs.getString(5));
				per.setPertypeno(rs.getInt(6));
				perList.add(per);
			}

			for (int i = 0; i < num; i++) {
				rows[i][0] = perList.get(i).getPertel();
				rows[i][1] = perList.get(i).getPername();
				rows[i][2] = idtypeQuery(perList.get(i).getIdtypeno());
				rows[i][3] = perList.get(i).getPerid();
				rows[i][4] = usertypeQuery(perList.get(i).getPertypeno());
			}

			SQLHelper.closeConnection();
		} catch (SQLException ee) {
			System.out.println(ee);
		}
		return rows;
	}

	// 联系人信息更新
	public static int perInfoUpdate(String pertel, String pername, String idtype, String perid, String pertype) {
		int idtypeno = idtypenoQuery(idtype);
		int pertypeno = usertypenoQuery(pertype);

		String mysql;
		int k = 0;
		try {
			mysql = "update userperson set pername='" + pername + "',idtypeno=" + idtypeno + ",perid='" + perid
					+ "',pertypeno=" + pertypeno + " where pertel='" + pertel + "'";
			k = SQLHelper.executeUpdate(mysql);
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return k;
	}

	// 联系人删除
	public static int perInfoDelete(String pertel) {
		String mysql;
		int k = 0;
		try {
			mysql = "delete from userperson where pertel='" + pertel + "'";
			k = SQLHelper.executeUpdate(mysql);
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return k;
	}

	// 添加联系人
	public static int perInsert(String usertel, String pertel, String pername, String idtype, String perid,
			String pertype) {
		int idtypeno = idtypenoQuery(idtype);
		int pertypeno = usertypenoQuery(pertype);

		String mysql = "insert into userperson values('" + usertel + "','" + pertel + "','" + pername + "'," + idtypeno
				+ ",'" + perid + "'," + pertypeno + ")";
		int k = 0;
		try {
			k = SQLHelper.executeUpdate(mysql);
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return k;
	}

	// 个人信息显示
	public static Object[] userInfo(String usertel) {
		Object info[] = new Object[6];
		String mysql;

		try {
			mysql = "select usertel,username,idtypeno,id,usertypeno from user where usertel='" + usertel + "'";
			ResultSet rs = SQLHelper.executeQuery(mysql);

			if (rs.next()) {
				info[0] = rs.getString(1);
				info[1] = rs.getString(2);
				info[2] = idtypeQuery(rs.getInt(3));
				info[3] = rs.getString(4);
				info[4] = usertypeQuery(rs.getInt(5));
			}

			SQLHelper.closeConnection();
		} catch (SQLException ee) {
			System.out.println(ee);
		}
		return info;
	}

	// 个人信息更新
	public static int userinfoUpdate(String usertel, String username, int idtypeno, String id, int usertypeno) {
		String mysql;
		int k = 0;
		try {
			mysql = "update user set username='" + username + "',idtypeno=" + idtypeno + ",id='" + id + "',usertypeno="
					+ usertypeno + " where usertel='" + usertel + "'";
			k = SQLHelper.executeUpdate(mysql);
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return k;
	}

	// 个人密码更新
	public static int userpwdUpdate(String usertel, String userpwd) {
		String mysql;
		int k = 0;
		try {
			mysql = "update user set userpwd='" + userpwd + "' where usertel='" + usertel + "'";
			k = SQLHelper.executeUpdate(mysql);
			SQLHelper.closeConnection();
		} catch (Exception ee) {
			System.out.println(ee);
		}
		return k;
	}
}

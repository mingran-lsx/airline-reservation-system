package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dbutil.SQLHelper;
import entity.Manager;

public class ManagerDao {
    //Manager_Login
    public static int managerValidate(String managerno,String managerpwd) {
        ArrayList<Manager> managerList=new ArrayList<Manager>();
        int flag=0;

        String mysql = "select managerno,managerpwd from manager";
        String wheresql1 = " where managerno='" + managerno + "'";
        mysql = mysql + wheresql1;
        System.out.println(mysql);
        try {
            ResultSet rs = SQLHelper.executeQuery(mysql);
            System.out.println(rs);
            if (rs.next()) {
                Manager mangagers = new Manager();
                mangagers.setManagerno(rs.getString(1));
                mangagers.setManagerpwd(rs.getString(2));
                managerList.add(mangagers);
            }

            if (managerList.isEmpty())
                flag = -1;  //不存在该账号
            else {
                if (managerList.get(0).getManagerpwd().equals(managerpwd))
                    flag = 1; //密码正确
                else
                    flag = 2; //密码错误
            }
            SQLHelper.closeConnection();
            return flag;

        } catch (SQLException ee) {
            System.out.println(ee);
        }
        return flag;
    }

    //密码修改
    public static int ManpwdUpdate(String managerno,String managerpwd) {
        String mysql;
        int k = 0;
        try {
            mysql = "update manager set managerpwd='"+managerpwd+"' where managerno='"+managerno+"'";
            System.out.println(mysql);
            k = SQLHelper.executeUpdate(mysql);
            System.out.println(k);
            SQLHelper.closeConnection();
        } catch (Exception ee) {
            System.out.println(ee);
        }
        return k;
    }
}

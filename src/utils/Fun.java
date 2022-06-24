package utils;

import dbutil.SQLHelper;

public class Fun {
    public static int getIntByString(String mysql) {
        int i = 0;
        try {
            i = Integer.parseInt(SQLHelper.executeSingleQuery(mysql));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return i;
    }
}

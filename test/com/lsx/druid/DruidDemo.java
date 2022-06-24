package com.lsx.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class DruidDemo {
  public static void main(String[] args) {
    //1.导入jar包
    //2.创建数据源
    //3.获取连接
    //4.关闭连接
    try {
      Properties properties = new Properties();//配置文件
      properties.load(new FileInputStream("jdbc-demo/src/druid.properties"));//加载配置文件
      DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);//创建数据源
      Connection connection = dataSource.getConnection();//获取连接
      System.out.println(connection);
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}

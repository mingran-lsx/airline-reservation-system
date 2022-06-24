package com.lsx.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.lsx.pojo.Brand;
import org.testng.annotations.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BrandTest {
@Test
public void test() throws Exception {
    try{
      System.out.println("当前路径："+System.getProperty("user.dir"));
        Properties properties = new Properties();//配置文件
        properties.load(new FileInputStream("src/druid.properties"));//加载配置文件
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);//创建数据源
        Connection conn = dataSource.getConnection();//获取连接
        //进行数据库操作
//        String sql = "select * from brand";
        //插入数据
//        String sql = "insert into brand(name,image,url) values(?,?,?)";
        //更新数据
        //String sql = "update brand set name=? where id=?";
        //删除数据
        //String sql = "delete from brand where id=?";
        //查询数据
        String sql = "select * from brand";
        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //执行sql
        ResultSet resultSet = pstmt.executeQuery();//执行查询语句
        Brand brand = null;
                List<Brand> list =new ArrayList<>();
        while (resultSet.next()){
             brand = new Brand();
            brand.setId(resultSet.getInt("id"));
            brand.setName(resultSet.getString("name"));
            brand.setFirstChar(resultSet.getString("first_char"));
            brand.setCategoryId(resultSet.getInt("category_id"));
            brand.setLogo(resultSet.getString("logo"));
            brand.setOrderedIndex(resultSet.getInt("ordered_index"));
            list.add(brand);
        }
      System.out.println(list);
//        int i = pstmt.executeUpdate();
//        System.out.println(i);
        //关闭资源
        resultSet.close();
        pstmt.close();
        conn.close();

    }catch (Exception e){
        e.printStackTrace();
    }
}

}

package com.example.administrator.smarthome;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Administrator on 2021/4/2 0002.
 */

public class DBManager {
    public Connection ConnectionMysql(){
        try {
            //加载驱动类（前提：正确引入jar包）
            Class.forName("com.mysql.jdbc.Driver");
            long start = System.currentTimeMillis();
            //通过驱动管理者获取连接，连接到具体数据库（连接对象内部实际上包含了Socke对象，是一个远程的连接，比较耗时！这是Connetion对象管理的一个要点）
            //真正开发中，为了提高效率，都会使用连接池来管理连接对象
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/environmental","root","root");

            long end = System.currentTimeMillis();
            System.out.println(connection);
            System.out.println("建立连接，耗时："+(end-start)+"ms毫秒");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){


    }
}
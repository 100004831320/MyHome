package com.example.administrator.smarthome.server;

import com.mchange.v2.c3p0.ComboPooledDataSource;


import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Administrator on 2021/4/19 0019.
 */

public final class ConnectionManager {
    //使用单利模式创建数据库连接池
    private static ConnectionManager instance;
    private static ComboPooledDataSource dataSource;

    private ConnectionManager() throws SQLException{
        dataSource = new ComboPooledDataSource();

        /*dataSource.setUser("root");        //用户名
        dataSource.setPassword("root"); //密码
        dataSource.setJdbcUrl("jdbc:mysql://47.105.70.99:3306/environmental?serverTimezone=UTC");//数据库地址
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setInitialPoolSize(5); //初始化连接数
        dataSource.setMinPoolSize(1);//最小连接数
        dataSource.setMaxPoolSize(10);//最大连接数
        dataSource.setMaxStatements(50);//最长等待时间
        dataSource.setMaxIdleTime(60);//最大空闲时间，单位毫秒*/
    }
    public static final ConnectionManager getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public synchronized final Connection getConnection() {
        Connection conn = null;
        /*try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return conn;
    }
}

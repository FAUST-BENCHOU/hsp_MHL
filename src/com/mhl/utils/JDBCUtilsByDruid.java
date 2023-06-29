package com.mhl.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mysql.jdbc.Statement;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author FAUST
 * @version 1.0
 * @date 2023/6/17 16:24
 */
public class JDBCUtilsByDruid {
    public static DataSource dataSource =null;

    static {
        try {
            // 初始化数据
            Properties properties = new Properties();
            properties.load(new FileInputStream("src\\druid.properties"));
            dataSource = (DataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(ResultSet resultSet, Statement statement, Connection connection) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


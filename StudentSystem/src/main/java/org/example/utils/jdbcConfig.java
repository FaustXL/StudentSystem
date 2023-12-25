package org.example.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class jdbcConfig {

    private static Connection connectionFactory;

    static {
        try {
            Properties prp = new Properties();
            prp.load(new FileInputStream("src/main/resources/jdbcConfig.properties"));
            DataSource dataSource = DruidDataSourceFactory.createDataSource(prp);
            connectionFactory = dataSource.getConnection();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception{
        return connectionFactory;
    }
}

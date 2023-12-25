package org.example;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.example.UI.MainJFrame;
import org.example.UI.loginFrame;
import org.example.utils.jdbcConfig;

import javax.sql.DataSource;
import java.io.FileInputStream;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class App 
{
    public static void main( String[] args ) throws Exception {
        new MainJFrame(1);
    }
}

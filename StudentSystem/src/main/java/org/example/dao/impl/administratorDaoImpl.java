package org.example.dao.impl;

import org.example.dao.administratorDao;
import org.example.domain.administrator;
import org.example.domain.studentUser;
import org.example.utils.jdbcConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class administratorDaoImpl implements administratorDao {
    @Override
    public administrator selectAdministratorByUsername(String username) throws Exception {
        Connection connection = jdbcConfig.getConnection();
        String sql = "select * from administrator where administrator_username = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,username);
        ResultSet resultSet = preparedStatement.executeQuery();

        administrator administrator = new administrator();
        while (resultSet.next()){
            administrator.setAdministratorId(resultSet.getInt("administrator_id"));
            administrator.setAdministratorUsername(resultSet.getString("administrator_username"));
            administrator.setAdministratorPassword(resultSet.getString("administrator_password"));
        }
        System.out.println(administrator);
        return administrator;
    }
}

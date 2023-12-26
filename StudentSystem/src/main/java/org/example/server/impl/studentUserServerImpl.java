package org.example.server.impl;

import org.example.dao.impl.studentUserDaoImpl;
import org.example.domain.studentUser;
import org.example.server.studentUserServer;

public class studentUserServerImpl implements studentUserServer {
    private studentUserDaoImpl studentUserDao = new studentUserDaoImpl();

    @Override
    public boolean isLoginSucceed(studentUser s) throws Exception {
        studentUser studentUser = studentUserDao.selectByUsername(s.getUsername());
        return s.getPassword().equals(studentUser.getPassword());
    }
}

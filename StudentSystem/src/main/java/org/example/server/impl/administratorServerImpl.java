package org.example.server.impl;

import org.example.dao.impl.administratorDaoImpl;
import org.example.domain.administrator;
import org.example.server.administratorServer;

public class administratorServerImpl implements administratorServer {
    private administratorDaoImpl administratorDao = new administratorDaoImpl();

    public boolean adminLogin(administrator admin) throws Exception {
        administrator administrator = administratorDao.selectAdministratorByUsername(admin.getAdministratorUsername());
        return admin.getAdministratorPassword().equals(administrator.getAdministratorPassword());
    }
}

package org.example.dao;

import org.example.domain.administrator;

public interface administratorDao {
    administrator selectAdministratorByUsername(String username) throws Exception;
}

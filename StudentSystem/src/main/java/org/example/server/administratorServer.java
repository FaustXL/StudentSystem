package org.example.server;

import org.example.domain.administrator;

public interface administratorServer {
    boolean adminLogin(administrator admin) throws Exception;
}

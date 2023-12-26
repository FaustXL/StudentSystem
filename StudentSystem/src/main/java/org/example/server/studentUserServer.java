package org.example.server;

import org.example.domain.studentUser;

public interface studentUserServer {
    boolean isLoginSucceed(studentUser s) throws Exception;
}

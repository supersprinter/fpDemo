package com.finnplay.demo.service;

public interface SecurityService {
    //String findLoggedInUsername();
    void autoLogin(String username, String password);
}

package net.cserny.tdd.servlet.service;

public interface AuthenticationService {
    boolean isValidLogin(String username, String password);
}

package net.cserny.tdd.servlet;

import net.cserny.tdd.servlet.service.AuthenticationService;

import java.util.HashMap;
import java.util.Map;

public class FakeAuthenticationService implements AuthenticationService {
    private Map<String, String> users = new HashMap<>();

    public void addUser(String user, String pass) {
        users.put(user, pass);
    }

    public void clearUsers() {
        users.clear();
    }

    @Override
    public boolean isValidLogin(String username, String password) {
        return users.containsKey(username) && password.equals(users.get(username));
    }
}

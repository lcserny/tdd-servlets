package net.cserny.tdd.servlet;

import net.cserny.tdd.servlet.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("j_username");
        String password = req.getParameter("j_password");
        if (getAuthenticationService().isValidLogin(username, password)) {
            resp.sendRedirect("/frontpage");
            req.getSession().setAttribute("username", username);
        } else {
            resp.sendRedirect("/invalidLogin");
        }
    }

    protected AuthenticationService getAuthenticationService() {
        return null;
    }
}

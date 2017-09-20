package net.cserny.tdd.servlet.controller;

import net.cserny.tdd.servlet.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {
    private final AuthenticationService authenticationService;

    @Autowired
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        if (authenticationService.isValidLogin(username, password)) {
            return new ModelAndView("frontpage");
        }
        return new ModelAndView("wrongpassword");
    }
}

package net.cserny.tdd.servlet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SampleController {
    protected ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("viewname");
    }
}

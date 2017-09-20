package net.cserny.tdd.servlet;

import net.cserny.tdd.servlet.service.AuthenticationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class TestLoginServlet {
    public static final String CORRECT_PASSWORD = "correctpassword";
    public static final String VALID_USERNAME = "validuser";

    private LoginServlet servlet;
    private FakeAuthenticationService authenticationService;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        authenticationService = new FakeAuthenticationService();
        authenticationService.addUser(VALID_USERNAME, CORRECT_PASSWORD);

        servlet = new LoginServlet() {
            @Override
            protected AuthenticationService getAuthenticationService() {
                return authenticationService;
            }
        };

        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void wrongPasswordShouldRedirectToErrorPage() throws Exception {
        request.addParameter("j_username", VALID_USERNAME);
        request.addParameter("j_password", "wrongpassword");
        servlet.service(request, response);
        Assert.assertEquals("/invalidLogin", response.getRedirectedUrl());
    }

    @Test
    public void validLoginForwardsToFrontPageAndStoresUsername() throws Exception {
        request.addParameter("j_username", VALID_USERNAME);
        request.addParameter("j_password", CORRECT_PASSWORD);
        servlet.service(request, response);
        Assert.assertEquals("/frontpage", response.getRedirectedUrl());
        Assert.assertEquals(VALID_USERNAME, request.getSession().getAttribute("username"));
    }
}

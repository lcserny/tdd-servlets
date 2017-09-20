package net.cserny.tdd.servlet;

import net.cserny.tdd.servlet.configuration.TestBeanConfiguration;
import net.cserny.tdd.servlet.controller.LoginController;
import net.cserny.tdd.servlet.service.AuthenticationService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.ViewResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.PostConstruct;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestBeanConfiguration.class)
public class TestLoginController {
    public static final String CORRECT_PASSWORD = "correctpassword";
    public static final String VALID_USER = "validuser";

    @Autowired
    private AuthenticationService authenticationService;
    private MockMvc mockMvc;
    private MockHttpServletRequestBuilder requestBuilder;
    private ViewResultMatchers viewMatcher;

    @PostConstruct
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController(authenticationService)).build();
        requestBuilder = MockMvcRequestBuilders.get("/login");
        requestBuilder.param("j_username", VALID_USER);
        viewMatcher = MockMvcResultMatchers.view();
    }

    @After
    public void tearDown() throws Exception {
        ((FakeAuthenticationService) authenticationService).clearUsers();
    }

    @Test
    public void wrongPasswordShouldRedirectToErrorPage() throws Exception {
        requestBuilder.param("j_password", "nosuchpassword");

        mockMvc.perform(requestBuilder).andExpect(viewMatcher.name("wrongpassword"));
    }

    @Test
    public void validLoginForwardsToFront() throws Exception {
        ((FakeAuthenticationService) authenticationService).addUser(VALID_USER, CORRECT_PASSWORD);
        requestBuilder.param("j_password", CORRECT_PASSWORD);

        mockMvc.perform(requestBuilder).andExpect(viewMatcher.name("frontpage"));
    }
}

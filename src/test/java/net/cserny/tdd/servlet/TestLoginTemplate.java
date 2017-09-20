package net.cserny.tdd.servlet;

import org.junit.Test;

public class TestLoginTemplate extends AbstractVelocityTestCase {
    @Override
    protected String getWebRoot() {
        return "./websrc/velocity";
    }

    @Test
    public void previousUserNameIsRetained() throws Exception {
        String previousUsername = "Bob";
        setAttribute("username", previousUsername);
        render("/login.vtl");
        assertFormFieldValue("j_username", previousUsername);
    }
}

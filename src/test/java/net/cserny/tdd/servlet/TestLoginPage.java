package net.cserny.tdd.servlet;

public class TestLoginPage extends MyJspTestCase {
    public void testformFieldsArePresent() throws Exception {
        get("/login.jsp");
        form().shouldHaveField("j_username");
        form().shouldHaveField("j_password");
        form().shouldHaveSubmitButton("login");
    }

    public void testPreviousUsernameIsRetained() throws Exception {
        setRequestAttribute("j_username", "bob");
        get("/login.jsp");
        form().field("j_username").shouldHaveValue("bob");
    }
}

package net.cserny.tdd.servlet;

import net.sf.jsptest.HtmlTestCase;

public abstract class MyJspTestCase extends HtmlTestCase {
    @Override
    protected String getWebRoot() {
        return "src/test/resources/websrc/jsp";
    }
}

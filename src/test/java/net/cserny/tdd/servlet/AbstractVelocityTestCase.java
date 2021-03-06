package net.cserny.tdd.servlet;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Assert;
import org.junit.Before;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.StringWriter;

public abstract class AbstractVelocityTestCase {
    private VelocityContext context;
    protected Document document;

    @Before
    public void setUp() throws Exception {
        context = new VelocityContext();
    }

    protected void assertFormFieldValue(String name, String expectedValue) throws Exception {
        String xpath = xpathForField(name);
        assertNodeExists(xpath);
        String actual = getString(xpath + "/@value");
        Assert.assertEquals(expectedValue, actual);
    }

    private String getString(String xpath) throws Exception {
        return (String) evaluate(xpath, XPathConstants.STRING);
    }

    private void assertNodeExists(String xpath) throws Exception {
        Assert.assertNotNull("Node doesn't exist: " + xpath, getNode(xpath));
    }

    private Node getNode(String xpath) throws Exception {
        return (Node) evaluate(xpath, XPathConstants.NODE);
    }

    private Object evaluate(String xpath, QName type) throws Exception {
        XPath engine = XPathFactory.newInstance().newXPath();
//        return engine.evaluate(xpath, getResponse(), type);
        return engine.evaluate(xpath, type);
    }

    private Object getResponse() {
        return null;
    }

    private String xpathForField(String name) {
        return "//form//input[@name='" + name + "']";
    }

    protected String getWebRoot() {
        return ".";
    }

    protected void setAttribute(String name, Object value) {
        context.put(name, value);
    }

    protected void render(String templatePath) throws Exception {
        File templateFile = new File(getWebRoot(), templatePath);
        String template = readFileContent(templateFile);
        String renderedHtml = renderTemplate(template);
        this.document = parseAsXml(renderedHtml);
    }

    private String renderTemplate(String template) throws Exception {
        VelocityEngine engine = new VelocityEngine();
        engine.init();
        StringWriter writer = new StringWriter();
        engine.evaluate(context, writer, "test", template);
        return writer.toString();
    }

    private Document parseAsXml(String html) throws Exception {
        // omitted for brevity
        return null;
    }

    private String readFileContent(File file) throws Exception {
        // omitted for brevity
        return null;
    }
}

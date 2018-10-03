package com.potapovich.project.tag;


import com.potapovich.project.constant.Constant;
import com.potapovich.project.localization.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Displays a message depending on the role on the main page
 */
@SuppressWarnings("serial")
public class StartPageGreetingTag extends TagSupport {
    private String role;
    private static final Logger LOGGER = LogManager.getLogger();

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            String to;
            if (Constant.ADMIN.equalsIgnoreCase(role)) {
                to = new MessageManager((String) pageContext.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(role);
            } else if (Constant.DRIVER.equalsIgnoreCase(role)) {
                to = new MessageManager((String) pageContext.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(role);
            } else if (Constant.USER.equalsIgnoreCase(role)) {
                to = new MessageManager((String) pageContext.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(role);
            } else {
                to = new MessageManager((String) pageContext.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(Constant.GUEST);
            }
            pageContext.getOut().write("<h4>" + to + "</h4>");
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "StartPageGreetingException ", e);
        }
        return SKIP_BODY;
    }
}

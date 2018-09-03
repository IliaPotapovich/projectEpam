package com.potapovich.project.command;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.Router;
import com.potapovich.project.localization.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {

        String language = (String) request.getSession().getAttribute(Constant.LANGUAGE);
        request.getSession().invalidate();
        request.getSession().setAttribute(Constant.LANGUAGE, language);
        request.getSession().setAttribute(Constant.LOGOUT_MESSAGE,
                new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(Constant.LOGOUT_MESSAGE));

        return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.FORWARD);
    }
}

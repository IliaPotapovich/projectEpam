package com.potapovich.project.command;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.localization.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        request.setAttribute(Constant.WRONG_ACTION,
                new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(Constant.EMPTY_COMMAND));
        return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.FORWARD);
    }
}
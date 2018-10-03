package com.potapovich.project.command;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;

import javax.servlet.http.HttpServletRequest;

public class LanguageCommand implements Command {

    /**
     * Change the language of the application
     * @return Router with type FORWARD and current page
     */
    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession().setAttribute(Constant.LANGUAGE, request.getParameter(Constant.LANG));
        Router router;
        if (request.getSession().getAttribute(Constant.LANG_PAGE) != null) {
            router = new Router((String) request.getSession().getAttribute(Constant.LANG_PAGE), Router.Type.FORWARD);
        } else {
            router = new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.FORWARD);
        }
        return router;
    }
}

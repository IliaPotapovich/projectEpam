package com.potapovich.project.command;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class ForwardCommand implements Command {

    /**
     * Forwarding the user without any additional action
     * @return Router with type FORWARD
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        return new Router(request.getParameter(Constant.PAGE), Router.Type.FORWARD);
    }
}

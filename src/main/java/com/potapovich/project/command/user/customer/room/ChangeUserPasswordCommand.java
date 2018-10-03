package com.potapovich.project.command.user.customer.room;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.UserService;
import com.potapovich.project.validation.DataValidator;

import javax.servlet.http.HttpServletRequest;

public class ChangeUserPasswordCommand implements Command {

    private UserService userService;

    public ChangeUserPasswordCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Change the user's password in the user's room
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        try {
            String login = request.getParameter(Constant.REG_LOGIN);
            String oldPassword = request.getParameter(Constant.OLD_PASSWORD);
            String newPassword = request.getParameter(Constant.NEW_PASSWORD);
            if (!DataValidator.validation(Constant.VALID_LOGIN, login) ||
                    !DataValidator.validation(Constant.VALID_PASS, oldPassword, newPassword)){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            if (userService.changeUserPassword(login, oldPassword, newPassword)) {
                request.getSession().setAttribute(Constant.MESS_PASS_CHANGED,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_PASS_CHANGED));
                router = new Router(Constant.PATH_PAGE_USER_ROOM, Router.Type.FORWARD);
            } else {
                request.getSession().setAttribute(Constant.LOGIN_ERROR,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.LOGIN_ERROR));
                router = new Router(Constant.PATH_PAGE_CHANGE_USER_PASSWORD, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("ChangeUserPasswordCommandError ", e);
        }
        return router;
    }
}

package com.potapovich.project.command.user.customer.login;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Customer;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.Route;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserLoginCommand implements Command {

    private UserService receiver;

    public UserLoginCommand(UserService receiver) {
        this.receiver = receiver;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException{

        Router router;
        try {
            String enteredLogin = request.getParameter(Constant.PARAM_LOGIN);
            String enteredPass = request.getParameter(Constant.PARAM_PASSWORD);
            if (receiver.checkLogin(enteredLogin,enteredPass) ){

                Customer customer = receiver.loadUser();

                if (!customer.isBannedStatus()) {
                    request.getSession().setAttribute(Constant.ID, customer.getId());
                    request.getSession().setAttribute(Constant.NAME, customer.getName());
                    request.getSession().setAttribute(Constant.PHONE, customer.getPhoneNumber());
                    request.getSession().setAttribute(Constant.TRIP, customer.getCountOfTrip());
                    request.getSession().setAttribute(Constant.DISCOUNT, customer.getDiscountProcent());
                    request.getSession().setAttribute(Constant.ROLE, Constant.USER);
                    router = new Router(Constant.PATH_PAGE_MAIN, Router.Type.FORWARD);
                }
                else {
                    request.getSession().setAttribute(Constant.MESS_YOU_ARE_BLOCKED,
                            new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                    getMessage(Constant.MESS_YOU_ARE_BLOCKED));
                    router = new Router(Constant.PATH_PAGE_LOGIN, Router.Type.FORWARD);
                }
            }
            else {
                    request.getSession().setAttribute(Constant.ERROR_LOGIN_PASS_MESSAGE,
                            new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                    getMessage(Constant.LOGIN_ERROR));

                router = new Router(Constant.PATH_PAGE_LOGIN, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("UserLoginCommandError ", e);
        }
        return router;

    }
}

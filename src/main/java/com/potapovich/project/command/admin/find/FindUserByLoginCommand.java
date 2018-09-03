package com.potapovich.project.command.admin.find;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.Customer;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.UserService;

import javax.servlet.http.HttpServletRequest;

public class FindUserByLoginCommand implements Command {

    private UserService userService;

    public FindUserByLoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        Customer customer;
        try {
            String login = request.getParameter(Constant.FIND_USER_BY_LOGIN);
            customer = userService.loadUser(login);

            if (customer.getId()!=0){
                request.getSession().setAttribute(Constant.USER_ID,customer.getId());
                request.getSession().setAttribute(Constant.USER_NAME,customer.getName());
                request.getSession().setAttribute(Constant.USER_PHONE,customer.getPhoneNumber());
                request.getSession().setAttribute(Constant.USER_BANNED_STATUS,customer.isBannedStatus());
                request.getSession().setAttribute(Constant.USER_DISCOUNT,customer.getDiscountProcent());
                request.getSession().setAttribute(Constant.USER_COUNT_OF_TRIP,customer.getCountOfTrip());
                request.getSession().setAttribute(Constant.USER_LOGIN,customer.getLogin());
                request.getSession().setAttribute(Constant.USER_PASSWORD,customer.getPassword());
                router = new Router(Constant.PATH_PAGE_FIND_USER, Router.Type.FORWARD);
            }
            else {
                request.getSession().setAttribute(Constant.MESS_LOGIN_IS_NOT_EXIST,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_LOGIN_IS_NOT_EXIST));
                router = new Router(Constant.PATH_PAGE_USER_FIND, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindUserByLoginCommandError ", e);
        }
        return router;
    }
}

package com.potapovich.project.command.user.customer.login;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.Customer;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.logic.UserService;

import javax.servlet.http.HttpServletRequest;

public class EnterInUserRoomCommand implements Command {

    private UserService userService;

    public EnterInUserRoomCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;

        if (request.getSession().getAttribute(Constant.ROLE)!=null &&
                request.getSession().getAttribute(Constant.ROLE).equals(Constant.USER)){
           int customerId = (int) request.getSession().getAttribute(Constant.ID);
            Customer customer;
            try {
                customer = userService.loadUser(customerId);
            } catch (LogicException e) {
                throw new CommandException("EnterInUserRoomCommandError ", e);
            }
            request.getSession().setAttribute(Constant.ID,customer.getId());
            request.getSession().setAttribute(Constant.NAME,customer.getName());
            request.getSession().setAttribute(Constant.PHONE,customer.getPhoneNumber());
            request.getSession().setAttribute(Constant.TRIP,customer.getCountOfTrip());
            request.getSession().setAttribute(Constant.DISCOUNT,customer.getDiscountProcent());
            request.getSession().setAttribute(Constant.ROLE, Constant.USER);

            router = new Router(Constant.PATH_PAGE_USER_ROOM, Router.Type.FORWARD);
        }

        else {
            router = new Router(Constant.PATH_PAGE_LOGIN, Router.Type.FORWARD);
        }
        return router;
    }
}

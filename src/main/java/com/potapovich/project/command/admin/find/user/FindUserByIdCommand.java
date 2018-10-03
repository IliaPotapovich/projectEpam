package com.potapovich.project.command.admin.find.user;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Customer;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.UserService;
import com.potapovich.project.validation.DataValidator;

import javax.servlet.http.HttpServletRequest;

public class FindUserByIdCommand implements Command {

    private UserService userService;

    public FindUserByIdCommand(UserService userService) {
        this.userService = userService;
    }

    /**
     * Find a user using a personal number
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        Customer customer;
        try {
            request.getSession().removeAttribute(Constant.USER_LIST);
            String id = request.getParameter(Constant.FIND_USER_BY_ID);
            if (!DataValidator.validation(Constant.VALID_NUMBER, id)){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            int userId = Integer.parseInt(id);
            customer = userService.loadUser(userId);
            if (customer.getId() != 0) {
                request.getSession().setAttribute(Constant.USER_ID, customer.getId());
                request.getSession().setAttribute(Constant.USER_NAME, customer.getName());
                request.getSession().setAttribute(Constant.USER_PHONE, customer.getPhoneNumber());
                request.getSession().setAttribute(Constant.USER_BANNED_STATUS, customer.isBannedStatus());
                request.getSession().setAttribute(Constant.USER_DISCOUNT, customer.getDiscountProcent());
                request.getSession().setAttribute(Constant.USER_COUNT_OF_TRIP, customer.getCountOfTrip());
                request.getSession().setAttribute(Constant.USER_LOGIN, customer.getLogin());
                request.getSession().setAttribute(Constant.USER_PASSWORD, customer.getPassword());
                router = new Router(Constant.PATH_PAGE_FIND_USER, Router.Type.FORWARD);
            } else {
                request.getSession().setAttribute(Constant.MESS_ID_IS_NOT_EXIST,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_ID_IS_NOT_EXIST));
                router = new Router(Constant.PATH_PAGE_USER_FIND, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindUserByIdCommandError ", e);
        }
        return router;
    }
}

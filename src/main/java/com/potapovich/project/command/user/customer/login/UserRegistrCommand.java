package com.potapovich.project.command.user.customer.login;

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

public class UserRegistrCommand implements Command {

    private UserService receiver;

    public UserRegistrCommand(UserService receiver) {
        this.receiver = receiver;
    }

    /**
     * New User Registration
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        try {
            String regLogin = request.getParameter(Constant.REG_LOGIN);
            String regPass = request.getParameter(Constant.REG_PASSWORD);
            String regName = request.getParameter(Constant.REG_NAME);
            String regPhone = request.getParameter(Constant.REG_PHONE);
            if (!DataValidator.validation(Constant.VALID_LOGIN, regLogin) ||
                    !DataValidator.validation(Constant.VALID_PASS, regPass) ||
                            !DataValidator.validation(Constant.VALID_NAME, regName) ||
                                    !DataValidator.validation(Constant.VALID_PHONE, regPhone)){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            Customer customer = new Customer(regLogin, regPass, regName, regPhone);
            if (receiver.userRegistration(customer)) {
                router = new Router(Constant.PATH_PAGE_LOGIN, Router.Type.FORWARD);
                request.getSession().setAttribute(Constant.REG_SUCCESS, new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(Constant.REG_SUCCESS));
            } else {
                request.getSession().setAttribute(Constant.REGISTR_ERROR, new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(Constant.REGISTR_ERROR));
                router = new Router(Constant.PATH_PAGE_REGISTER, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("UserRegistrCommandError ", e);
        }
        return router;
    }
}

package com.potapovich.project.command.user.customer.login;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Customer;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.UserService;

import javax.servlet.http.HttpServletRequest;

public class UserRegistrCommand implements Command {

     private UserService receiver;

    public UserRegistrCommand(UserService receiver) {
        this.receiver = receiver;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router;

        try {
            String regLogin = request.getParameter(Constant.REG_LOGIN);
            String regPass = request.getParameter(Constant.REG_PASSWORD);
            String regName = request.getParameter(Constant.REG_NAME);
            String regPhone = request.getParameter(Constant.REG_PHONE);
            Customer customer =new Customer(regLogin,regPass,regName,regPhone);

            if (!receiver.userRegistration(customer)){
               request.getSession().setAttribute(Constant.REGISTR_ERROR,"LOGIN ERROR");
               router = new Router(Constant.PATH_PAGE_REGISTER, Router.Type.FORWARD);
           }
           else {
                router = new Router(Constant.PATH_PAGE_LOGIN, Router.Type.FORWARD);
               request.getSession().setAttribute(Constant.REG_SUCCESS, new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                       getMessage(Constant.REG_SUCCESS));
           }
        } catch (LogicException e) {
            throw new CommandException("UserRegistrCommandError ", e);
        }

        return router;
    }
}

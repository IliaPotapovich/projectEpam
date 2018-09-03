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
import java.util.List;

public class FindListOfUsersCommand implements Command {


    private UserService userService;


    public FindListOfUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        List<Customer> listOfUsers;
        Router router;
        try {
            listOfUsers = userService.findAllUsers();
        } catch (LogicException e) {
            throw new CommandException("FindListOfUsersCommandError ", e);
        }

        if (!listOfUsers.isEmpty()) {
            request.getSession().setAttribute(Constant.USER_LIST, listOfUsers);
            router = new Router(Constant.PATH_PAGE_LIST_OF_USERS, Router.Type.FORWARD);
        }
        else{
            request.getSession().setAttribute(Constant.USERS_NOT_EXIST,
                    new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                            getMessage(Constant.USERS_NOT_EXIST));

            router = new Router(Constant.PATH_PAGE_USER_FIND, Router.Type.FORWARD);
        }
        return router;
    }
}

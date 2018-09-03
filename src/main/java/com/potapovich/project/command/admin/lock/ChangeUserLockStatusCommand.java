package com.potapovich.project.command.admin.lock;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.Customer;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.logic.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeUserLockStatusCommand implements Command {

   private UserService userService;

    public ChangeUserLockStatusCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException{

        Router router;
        boolean updateStatus;
        try {
            String id = request.getParameter(Constant.USER_ID_CHOOSE);
            int userId = Integer.parseInt(id);
            updateStatus = userService.updateUserLockStatus(userId);
            if (request.getSession().getAttribute(Constant.USER_LIST)!=null){
                List<Customer> listOfCustomers = userService.findAllUsers();
                request.getSession().setAttribute(Constant.USER_LIST, listOfCustomers);
                router = new Router(Constant.PATH_PAGE_LIST_OF_USERS, Router.Type.FORWARD);
            }
            else {
                request.getSession().removeAttribute(Constant.USER_BANED_STATUS);
                request.getSession().setAttribute(Constant.USER_BANED_STATUS, updateStatus);
                router = new Router(Constant.PATH_PAGE_FIND_USER, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("ChangeUserLockStatusCommandError ", e);
        }
        return router;
    }
}

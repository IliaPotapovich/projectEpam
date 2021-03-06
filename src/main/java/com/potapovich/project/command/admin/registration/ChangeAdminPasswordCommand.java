package com.potapovich.project.command.admin.registration;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Admin;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.AdminService;
import com.potapovich.project.validation.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeAdminPasswordCommand implements Command {

    private AdminService adminService;

    public ChangeAdminPasswordCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Change administrator password
     * @return Router with type FORWARD because re-writing to the database is prohibited by the application logic
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        try {
            String login = request.getParameter(Constant.REG_LOGIN);
            String oldPassword = request.getParameter(Constant.OLD_PASSWORD);
            String newPassword = request.getParameter(Constant.NEW_PASSWORD);
            if (!DataValidator.validation(Constant.VALID_NAME, login) ||
                    !DataValidator.validation(Constant.VALID_PASS, oldPassword, newPassword)){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            if (adminService.changeAdminPassword(login, oldPassword, newPassword)) {
                List<Admin> listOfAdminName = adminService.findListOfAdminName();
                request.getSession().setAttribute(Constant.ADMIN_LIST, listOfAdminName);
                request.getSession().setAttribute(Constant.MESS_PASS_CHANGED,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_PASS_CHANGED));
                router = new Router(Constant.PATH_PAGE_LIST_OF_ADMINS, Router.Type.FORWARD);
            } else {
                request.getSession().setAttribute(Constant.LOGIN_ERROR,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.LOGIN_ERROR));
                router = new Router(Constant.PATH_PAGE_CHANGE_ADMIN_PASSWORD, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("ChangeAdminPasswordCommandError ", e);
        }
        return router;
    }
}

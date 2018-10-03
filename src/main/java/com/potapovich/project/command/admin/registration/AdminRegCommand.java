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

public class AdminRegCommand implements Command {

    private AdminService adminService;

    public AdminRegCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Administrator registration
     * @return Router with type FORWARD because re-writing to the database is prohibited by the application logic
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        try {
            String adminLogin = request.getParameter(Constant.REG_ADMIN_NAME);
            String adminPassword = request.getParameter(Constant.REG_ADMIN_PASSWORD);
            if (!DataValidator.validation(Constant.VALID_NAME, adminLogin) ||
                    !DataValidator.validation(Constant.VALID_PASS, adminPassword)){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            Admin admin = new Admin(adminLogin, adminPassword);
            if (adminService.adminRegistration(admin)) {
                request.getSession().setAttribute(Constant.REG_SUCCESS, new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(Constant.REG_SUCCESS));
                router = new Router(Constant.PATH_PAGE_ADMIN_START_PAGE, Router.Type.FORWARD);
            } else {
                request.getSession().setAttribute(Constant.REGISTR_ERROR, new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(Constant.REG_LOGIN_EXIST));

                router = new Router(Constant.PATH_PAGE_ADMIN_REGISTER, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("AdminRegCommandError ", e);
        }
        return router;
    }
}
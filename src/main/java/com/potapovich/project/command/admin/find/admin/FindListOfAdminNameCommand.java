package com.potapovich.project.command.admin.find.admin;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Admin;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.logic.AdminService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindListOfAdminNameCommand implements Command {

    private AdminService adminService;

    public FindListOfAdminNameCommand(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * Find a list of administrators
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        List<Admin> listOfAdminName;
        try {
            listOfAdminName = adminService.findListOfAdminName();
            request.getSession().setAttribute(Constant.ADMIN_LIST, listOfAdminName);
            router = new Router(Constant.PATH_PAGE_LIST_OF_ADMINS, Router.Type.FORWARD);
        } catch (LogicException e) {
            throw new CommandException("FindListOfAdminNameCommandError ", e);
        }
        return router;
    }
}

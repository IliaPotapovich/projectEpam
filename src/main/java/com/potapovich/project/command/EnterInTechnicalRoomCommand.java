package com.potapovich.project.command;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Customer;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.logic.AdminService;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;

public class EnterInTechnicalRoomCommand implements Command {

    private TaxiService taxiService;
    private AdminService adminService;

    public EnterInTechnicalRoomCommand(TaxiService taxiService, AdminService adminService) {
        this.taxiService = taxiService;
        this.adminService = adminService;
    }



    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;

        if (request.getSession().getAttribute(Constant.ROLE)!=null &&
                request.getSession().getAttribute(Constant.ROLE).equals(Constant.DRIVER)) {
            router = new Router(Constant.PATH_PAGE_TAXI_START_PAGE, Router.Type.FORWARD);
        }
        else {
            router = new Router(Constant.PATH_PAGE_TECHNICAL_OFFICE, Router.Type.FORWARD);
        }

        if (request.getSession().getAttribute(Constant.ROLE)!=null &&
                request.getSession().getAttribute(Constant.ROLE).equals(Constant.ADMIN)) {
            router = new Router(Constant.PATH_PAGE_ADMIN_START_PAGE, Router.Type.FORWARD);
        }
        return router;
    }
}

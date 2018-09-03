package com.potapovich.project.command;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.AdminService;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;

public class TechnicalLoginCommand implements Command {

    private TaxiService taxiService;
    private AdminService adminService;

    public TechnicalLoginCommand(TaxiService taxiService, AdminService adminService) {
        this.taxiService = taxiService;
        this.adminService = adminService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router;

        String enteredLogin = request.getParameter(Constant.TECH_LOGIN);
        String enteredPass = request.getParameter(Constant.TECH_PASSWORD);
        try {
            if (taxiService.isExistTaxiDriverNamePass(enteredLogin,enteredPass) ){

                TaxiDriver taxiDriver = taxiService.findDriverByNameAndPassword(enteredLogin,enteredPass);
                if (!taxiDriver.isStatus()) {
                    router = new Router(Constant.PATH_PAGE_TAXI_START_PAGE, Router.Type.FORWARD);
                    request.getSession().setAttribute(Constant.ROLE, Constant.DRIVER);
                    request.getSession().setAttribute(Constant.TAXI_NAME, enteredLogin);
                    request.getSession().setAttribute(Constant.TAXI_PASS, enteredPass);
                    request.getSession().setAttribute(Constant.DRIVER_ID, taxiDriver.getDriverId());
                }
                else {
                    router = new Router(Constant.PATH_PAGE_TECHNICAL_OFFICE, Router.Type.FORWARD);
                    request.getSession().setAttribute(Constant.MESS_YOU_ARE_BLOCKED,
                            new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                    getMessage(Constant.MESS_YOU_ARE_BLOCKED));
                }
            }

            else {
                request.getSession().setAttribute(Constant.ERROR_TECHN_LOGIN_PASS_MESSAGE,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.LOGIN_ERROR));

                router = new Router(Constant.PATH_PAGE_TECHNICAL_OFFICE, Router.Type.FORWARD);
            }

            if (adminService.isAdminLoginPasswordExist(enteredLogin,enteredPass) ){
                request.getSession().setAttribute(Constant.ROLE, Constant.ADMIN);

                router = new Router(Constant.PATH_PAGE_ADMIN_START_PAGE, Router.Type.FORWARD);
            }

        } catch (LogicException e) {
            throw new CommandException("TechnicalLoginCommandError ", e);
        }
        return router;
    }
}

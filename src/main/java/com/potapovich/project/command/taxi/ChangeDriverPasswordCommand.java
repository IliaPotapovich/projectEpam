package com.potapovich.project.command.taxi;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;

public class ChangeDriverPasswordCommand implements Command {

    private TaxiService taxiService;

    public ChangeDriverPasswordCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router;
        try {
            String login = request.getParameter(Constant.REG_LOGIN);
            String oldPassword = request.getParameter(Constant.OLD_PASSWORD);
            String newPassword = request.getParameter(Constant.NEW_PASSWORD);
            int driverId = (int) request.getSession().getAttribute(Constant.DRIVER_ID);
            if (taxiService.changeDriverPassword(login,oldPassword,newPassword, driverId)){

                request.getSession().setAttribute(Constant.TAXI_PASS,newPassword);

                request.getSession().setAttribute(Constant.MESS_PASS_CHANGED,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_PASS_CHANGED));
                router = new Router(Constant.PATH_PAGE_TAXI_START_PAGE, Router.Type.FORWARD);
            }
            else {
                request.getSession().setAttribute(Constant.LOGIN_ERROR,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.LOGIN_ERROR));
                router = new Router(Constant.PATH_PAGE_CHANGE_DRIVER_PASSWORD, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("ChangeDriverPasswordCommandError ", e);
        }

        return router;
    }
}

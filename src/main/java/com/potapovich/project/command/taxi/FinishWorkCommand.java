package com.potapovich.project.command.taxi;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;

public class FinishWorkCommand implements Command {

    private TaxiService taxiService;

    public FinishWorkCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Confirmation of the end of the working day for the taxi driver. Excludes visibility in the list of active taxis
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            int driverId = (int) request.getSession().getAttribute(Constant.DRIVER_ID);
            if (taxiService.finishTaxiWork(driverId)) {
                request.getSession().setAttribute(Constant.MESS_DRIVER_FINISH_WORK,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_DRIVER_FINISH_WORK));
            } else {
                request.getSession().setAttribute(Constant.MESS_DRIVER_WORK_WAS_NOT_STARTED,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_DRIVER_WORK_WAS_NOT_STARTED));
            }
        } catch (LogicException e) {
            throw new CommandException("FinishWorkCommandError ", e);
        }
        return new Router(Constant.PATH_PAGE_TAXI_START_PAGE, Router.Type.FORWARD);
    }
}

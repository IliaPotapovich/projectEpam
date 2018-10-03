package com.potapovich.project.command.user.common;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.Taxi;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowListOfWorkingTaxiCommand implements Command {

    private TaxiService taxiService;

    public ShowListOfWorkingTaxiCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Show the list of active taxis
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            List<Taxi> listOfTaxi;
            listOfTaxi = taxiService.findListOfWorkingTaxi();
            if (!listOfTaxi.isEmpty()) {
                request.getSession().setAttribute(Constant.TAXI_WORKING_LIST, listOfTaxi);
                request.getSession().setAttribute(Constant.MESS_TAXI_LIST_OF_WORKING_TAXI,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_TAXI_LIST_OF_WORKING_TAXI));
            } else {
                request.getSession().setAttribute(Constant.TAXI_LIST_IS_NOT_EXIST,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.TAXI_LIST_IS_NOT_EXIST));
            }
        } catch (LogicException e) {
            throw new CommandException("ShowListOfWorkingTaxiCommandError ", e);
        }
        return new Router(Constant.PATH_PAGE_LIST_OF_WORKING_TAXI, Router.Type.FORWARD);
    }
}

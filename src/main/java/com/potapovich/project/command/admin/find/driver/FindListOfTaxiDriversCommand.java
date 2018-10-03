package com.potapovich.project.command.admin.find.driver;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindListOfTaxiDriversCommand implements Command {

    private TaxiService taxiService;

    public FindListOfTaxiDriversCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Find a list of existing drivers
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        List<TaxiDriver> listOfDrivers;
        Router router;
        try {
            listOfDrivers = taxiService.findListOfDriver();
            if (!listOfDrivers.isEmpty()) {
                request.getSession().setAttribute(Constant.DRIVER_LIST, listOfDrivers);
                router = new Router(Constant.PATH_PAGE_LIST_OF_DRIVERS, Router.Type.FORWARD);
            } else {
                request.getSession().setAttribute(Constant.DRIVER_LIST_IS_NOT_EXIST,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.DRIVER_LIST_IS_NOT_EXIST));
                router = new Router(Constant.PATH_PAGE_TAXI_FIND, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindListOfTaxiDriversCommandError ", e);
        }
        return router;
    }
}

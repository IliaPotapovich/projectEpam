package com.potapovich.project.command.admin.find.car;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.TaxiCar;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindListOfTaxiCarCommand implements Command {

    private TaxiService taxiService;

    public FindListOfTaxiCarCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Find a list existing taxis
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        List<TaxiCar> listOfCars;
        Router router;
        try {
            listOfCars = taxiService.findListOfCars();
            if (!listOfCars.isEmpty()) {
                request.getSession().setAttribute(Constant.CAR_LIST, listOfCars);
                router = new Router(Constant.PATH_PAGE_LIST_OF_CARS, Router.Type.FORWARD);
            } else {
                request.getSession().setAttribute(Constant.СAR_LIST_IS_NOT_EXIST,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.СAR_LIST_IS_NOT_EXIST));
                router = new Router(Constant.PATH_PAGE_TAXI_FIND, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindListOfTaxiCarCommandError ", e);
        }
        return router;
    }
}

package com.potapovich.project.command.admin.find;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.TaxiCar;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindListOfDeletedCarsCommand implements Command {

    private TaxiService taxiService;

    public FindListOfDeletedCarsCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        List<TaxiCar> listOfCars;
        try {
            listOfCars = taxiService.findDeletedCars();

            if (!listOfCars.isEmpty()) {
                request.getSession().setAttribute(Constant.CAR_LIST, listOfCars);
                router = new Router(Constant.PATH_PAGE_DELETED_CARS, Router.Type.FORWARD);
            }
            else{
                request.getSession().setAttribute(Constant.DELETED_CARS_IS_NOT_EXIST,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.DELETED_CARS_IS_NOT_EXIST));
                router = new Router(Constant.PATH_PAGE_ARCHIVES, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindListOfDeletedCarsError ", e);
        }

        return router;
    }
}

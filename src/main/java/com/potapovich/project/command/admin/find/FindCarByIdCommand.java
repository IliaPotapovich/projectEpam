package com.potapovich.project.command.admin.find;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.TaxiCar;
import com.potapovich.project.command.Command;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;

public class FindCarByIdCommand implements Command {
    private TaxiService taxiService;

    public FindCarByIdCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router;
        try {
            TaxiCar car;
            String id = request.getParameter(Constant.TAXI_CAR_ID);
            int carId = Integer.parseInt(id);
            car = taxiService.findCarById(carId);

            if (car.getCarId()!=0){
                request.getSession().setAttribute(Constant.CAR_ID,car.getCarId());
                request.getSession().setAttribute(Constant.MODEL,car.getModel());
                request.getSession().setAttribute(Constant.OWNER_ID,car.getOwnerId());
                request.getSession().setAttribute(Constant.YEAR,car.getYearOFManufacture());
                request.getSession().setAttribute(Constant.IMAGE_CAR,car.getImageCarId());

                router = new Router(Constant.PATH_PAGE_FIND_CAR_BY_ID, Router.Type.FORWARD);
            }
            else {
                request.getSession().setAttribute(Constant.CAR_ID_ERROR_MESSAGE,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.CAR_IS_NOT_EXIST));

                router = new Router(Constant.PATH_PAGE_TAXI_FIND, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindCarByIdCommandError ", e);
        }


        return router;
    }
}

package com.potapovich.project.command.admin.find.car;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.TaxiCar;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;
import com.potapovich.project.validation.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindCarByOwnerCommand implements Command {
    private TaxiService taxiService;

    public FindCarByOwnerCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Find a list of taxi cars using the personal number of the car owner
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        List<TaxiCar> listOfCars;
        try {
            String id = request.getParameter(Constant.TAXI_CAR_OWNER_ID);
            if (!DataValidator.validation(Constant.VALID_NUMBER, id)){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            int ownerId = Integer.parseInt(id);
            listOfCars = taxiService.findCarsByOwnerId(ownerId);
            if (!listOfCars.isEmpty()) {
                request.getSession().setAttribute("list", listOfCars);
                router = new Router(Constant.PATH_PAGE_FIND_CAR, Router.Type.FORWARD);
            } else {
                request.getSession().setAttribute(Constant.CAR_OWNER_ID_ERROR_MESSAGE,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.CAR_IS_NOT_EXIST));

                router = new Router(Constant.PATH_PAGE_TAXI_FIND, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindCarByOwnerCommandError ", e);
        }
        return router;
    }
}

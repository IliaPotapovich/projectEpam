package com.potapovich.project.command.admin.registration;

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

public class TaxiCarRegCommand implements Command {

    private TaxiService taxiService;

    public TaxiCarRegCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Taxi Car Registration
     * @return Router with type REDIRECT to avoid re-writing to the database
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            if (request.getParameter(Constant.REG_CAR_MODEL) != null) {
                String carModel = request.getParameter(Constant.REG_CAR_MODEL);
                String ownerId = request.getParameter(Constant.REG_OWNER_ID);
                String yearOfManuf = request.getParameter(Constant.REG_YEAR_OF_MANUF);
                if (!DataValidator.validation(Constant.VALID_CAR_NAME, carModel) ||
                        !DataValidator.validation(Constant.VALID_NUMBER, ownerId, yearOfManuf)){
                    return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
                }
                int ownId = Integer.parseInt(ownerId);
                int yearOfManufacture = Integer.parseInt(yearOfManuf);
                TaxiCar car = new TaxiCar(carModel, ownId, yearOfManufacture);
                taxiService.taxiCarRegistration(car);
                request.getSession().setAttribute(Constant.REG_CAR_SUCCESS, new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(Constant.REG_SUCCESS));
            }
        } catch (LogicException e) {
            throw new CommandException("TaxiCarRegCommandError ", e);
        }
        return new Router(Constant.PATH_PAGE_ADMIN_START_PAGE, Router.Type.REDIRECT);
    }
}

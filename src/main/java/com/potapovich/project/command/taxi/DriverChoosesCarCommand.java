package com.potapovich.project.command.taxi;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.Taxi;
import com.potapovich.project.entity.TaxiCar;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;
import com.potapovich.project.validation.DataValidator;

import javax.servlet.http.HttpServletRequest;

public class DriverChoosesCarCommand implements Command {

    private TaxiService taxiService;

    public DriverChoosesCarCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Choosing a personal taxi car by a taxi driver
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        try {
            int driverId = (int) request.getSession().getAttribute(Constant.DRIVER_ID);
            String carId = request.getParameter(Constant.DESIRED_CAR_ID);
            if (!DataValidator.validation(Constant.VALID_NUMBER, carId)){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            int desiredCarId = Integer.parseInt(carId);
            TaxiDriver driver;
            driver = taxiService.findDriverById(driverId);
            TaxiCar car = taxiService.findCarById(desiredCarId);
            if (car.getCarId() != 0 && driver.getDriverId() == car.getOwnerId()) {
                request.getSession().setAttribute(Constant.IMAGE_CAR, car.getImageCarId());
                request.getSession().setAttribute(Constant.DESIRED_CAR_ID, carId);
                request.getSession().setAttribute(Constant.DRIVER_ID, driver.getDriverId());
                request.getSession().setAttribute(Constant.DRIVER_NAME, driver.getDriverName());
                request.getSession().setAttribute(Constant.DRIVER_EXPERIENCE, driver.getExperience());
                request.getSession().setAttribute(Constant.DRIVER_STATUS, driver.isStatus());
                request.getSession().setAttribute(Constant.CAR_ID, car.getCarId());
                request.getSession().setAttribute(Constant.MODEL, car.getModel());
                request.getSession().setAttribute(Constant.OWNER_ID, car.getOwnerId());
                request.getSession().setAttribute(Constant.YEAR, car.getYearOFManufacture());
                Taxi taxi = new Taxi(driver, car);
                if (taxiService.startTaxiWork(taxi)) {
                    request.getSession().setAttribute(Constant.MESS_DRIVER_START_WORK,
                            new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                    getMessage(Constant.MESS_DRIVER_START_WORK));
                    router = new Router(Constant.PATH_PAGE_TAXI_ROOM, Router.Type.FORWARD);
                } else {
                    request.getSession().setAttribute(Constant.REG_DRIVER_EXIST,
                            new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                    getMessage(Constant.REG_DRIVER_EXIST));

                    router = new Router(Constant.PATH_PAGE_TAXI_START_PAGE, Router.Type.FORWARD);
                }
            } else {
                request.getSession().setAttribute(Constant.CAR_ID_ERROR_MESSAGE,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.CAR_IS_NOT_EXIST));

                router = new Router(Constant.PATH_PAGE_TAXI_START_PAGE, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("DriverChoosesCarCommandError ", e);
        }
        return router;
    }
}

package com.potapovich.project.command.admin.find.driver;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;
import com.potapovich.project.validation.DataValidator;

import javax.servlet.http.HttpServletRequest;

public class FindDriverByNameCommand implements Command {

    private TaxiService taxiService;

    public FindDriverByNameCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Find a driver by name
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        TaxiDriver driver;
        try {
            String name = request.getParameter(Constant.TAXI_DRIVER_NAME);
            if (!DataValidator.validation(Constant.VALID_NAME, name)){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            driver = taxiService.findDriverByName(name);
            if (driver.getDriverId() != 0) {
                request.getSession().setAttribute(Constant.DRIVER_ID, driver.getDriverId());
                request.getSession().setAttribute(Constant.DRIVER_NAME, driver.getDriverName());
                request.getSession().setAttribute(Constant.DRIVER_EXPERIENCE, driver.getExperience());
                request.getSession().setAttribute(Constant.DRIVER_STATUS, driver.isStatus());
                router = new Router(Constant.PATH_PAGE_FIND_DRIVER, Router.Type.FORWARD);
            } else {
                request.getSession().setAttribute(Constant.DRIVER_NAME_ERROR_MESSAGE,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.DRIVER_IS_NOT_EXIST));
                router = new Router(Constant.PATH_PAGE_TAXI_FIND, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindDriverByNameCommandError ", e);
        }
        return router;
    }
}

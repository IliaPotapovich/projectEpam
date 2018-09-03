package com.potapovich.project.command.admin.find;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.command.Command;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;

public class FindDriverByIdCommand implements Command {

    private TaxiService taxiService;

    public FindDriverByIdCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException{

        Router router;
        TaxiDriver driver;
        try {
            String id = request.getParameter(Constant.TAXI_DRIVER_ID);
            int driverId = Integer.parseInt(id);
            driver = taxiService.findDriverById(driverId);
        } catch (LogicException e) {
            throw new CommandException("FindDriverByIdCommandError ", e);
        }

        if (driver.getDriverId()!=0){
            request.getSession().setAttribute(Constant.DRIVER_ID,driver.getDriverId());
            request.getSession().setAttribute(Constant.DRIVER_NAME,driver.getDriverName());
            request.getSession().setAttribute(Constant.DRIVER_EXPERIENCE,driver.getExperience());
            request.getSession().setAttribute(Constant.DRIVER_STATUS,driver.isStatus());
            router = new Router(Constant.PATH_PAGE_FIND_DRIVER, Router.Type.FORWARD);
        }
        else {
            request.getSession().setAttribute(Constant.DRIVER_ERROR_MESSAGE,
                    new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                            getMessage(Constant.DRIVER_IS_NOT_EXIST));
            router = new Router(Constant.PATH_PAGE_TAXI_FIND, Router.Type.FORWARD);

        }
        return router;

    }
}

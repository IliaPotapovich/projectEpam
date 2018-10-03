package com.potapovich.project.command.admin.registration;

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

public class TaxiDriverRegCommand implements Command {

    private TaxiService taxiService;

    public TaxiDriverRegCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Taxi Driver Registration
     * @return Router with type REDIRECT to avoid re-writing to the database
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        try {
            if (request.getParameter(Constant.REG_DRIVER_NAME) != null) {
                String driverName = request.getParameter(Constant.REG_DRIVER_NAME);
                String driverPassword = request.getParameter(Constant.REG_DRIVER_PASSWORD);
                String driverExp = request.getParameter(Constant.REG_DRIVER_EXP);
                if (!DataValidator.validation(Constant.VALID_NAME, driverName) ||
                        !DataValidator.validation(Constant.VALID_PASS, driverPassword) ||
                        !DataValidator.validation(Constant.VALID_NUMBER, driverExp)){
                    return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
                }
                int driverExperience = Integer.parseInt(driverExp);
                TaxiDriver driver = new TaxiDriver(driverName, driverPassword, driverExperience);
                taxiService.taxiDriverRegistration(driver);
                request.getSession().setAttribute(Constant.REG_TAXI_SUCCESS, new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                        getMessage(Constant.REG_SUCCESS));
            }
        } catch (LogicException e) {
            throw new CommandException("TaxiDriverRegCommandError ", e);
        }
        return new Router(Constant.PATH_PAGE_ADMIN_START_PAGE, Router.Type.REDIRECT);
    }
}

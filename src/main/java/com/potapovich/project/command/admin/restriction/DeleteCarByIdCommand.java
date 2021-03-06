package com.potapovich.project.command.admin.restriction;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;
import com.potapovich.project.validation.DataValidator;

import javax.servlet.http.HttpServletRequest;

public class DeleteCarByIdCommand implements Command {

    private TaxiService taxiService;

    public DeleteCarByIdCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Remove the taxi car with a personal number help
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        try {
            String id = request.getParameter(Constant.CAR_DELETE_ID);
            if (!DataValidator.validation(Constant.VALID_NUMBER, id)){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            int driverId = Integer.parseInt(id);
            if (taxiService.deleteCar(driverId)) {
                request.getSession().setAttribute(Constant.MESS_DELETE_SUCCESS,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_DELETE_SUCCESS));
            } else {
                request.getSession().setAttribute(Constant.CAR_IS_NOT_EXIST,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.CAR_IS_NOT_EXIST));
            }
            router = new Router(Constant.PATH_PAGE_TAXI_FIND, Router.Type.FORWARD);
        } catch (LogicException e) {
            throw new CommandException("DeleteCarByIdError ", e);
        }
        return router;
    }
}

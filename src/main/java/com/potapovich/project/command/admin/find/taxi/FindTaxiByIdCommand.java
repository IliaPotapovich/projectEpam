package com.potapovich.project.command.admin.find.taxi;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.Taxi;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;
import com.potapovich.project.validation.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FindTaxiByIdCommand implements Command {

    private TaxiService taxiService;

    public FindTaxiByIdCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Find a taxi with help of personal number
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        try {
            String id = request.getParameter(Constant.TAXI_FIND_ID);
            if (!DataValidator.validation(Constant.VALID_NUMBER, id)){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            Taxi taxi;
            List<Taxi> listOfTaxi = new ArrayList<>();
            int taxiId = Integer.parseInt(id);
            taxi = taxiService.findTaxiById(taxiId);
            listOfTaxi.add(taxi);
            if (taxi.getId() != 0) {
                request.getSession().setAttribute(Constant.TAXI_APPROPRIATE_LIST, listOfTaxi);
                router = new Router(Constant.PATH_PAGE_FIND_TAXI, Router.Type.FORWARD);
            } else {
                request.getSession().setAttribute(Constant.TAXI_IS_NOT_EXIST_BY_THIS_ID,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.TAXI_IS_NOT_EXIST_BY_THIS_ID));
                router = new Router(Constant.PATH_PAGE_TRIP_FIND, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindTaxiByIdCommandError ", e);
        }
        return router;
    }
}

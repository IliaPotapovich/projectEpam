package com.potapovich.project.command.taxi;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.Taxi;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;
import com.potapovich.project.logic.TripService;

import javax.servlet.http.HttpServletRequest;

public class SetFreeWorkStatusCommand implements Command {

    private TaxiService taxiService;
    private TripService tripService;

    public SetFreeWorkStatusCommand(TaxiService taxiService, TripService tripService) {
        this.taxiService = taxiService;
        this.tripService = tripService;
    }

    /**
     * Refusal to travel or its cancellation. It is used by the taxi driver in the event that the client decides
     for a long time whether he will go or not. Either the browser was closed and the session was not closed
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        try {
            int driverId;
            if (request.getSession().getAttribute(Constant.DRIVER_ID) != null) {
                driverId = (int) request.getSession().getAttribute(Constant.DRIVER_ID);
            } else {
                return new Router(Constant.PATH_PAGE_TECHNICAL_OFFICE, Router.Type.FORWARD);
            }
            Taxi taxi = taxiService.findWorkingTaxiByDriverId(driverId);
            if (taxi.getId() != 0 && !taxi.isFree()) {
                taxiService.changeTaxiFreeStatusTrue(taxi.getId());
                request.getSession().setAttribute(Constant.MESS_TAXI_FREE_AGAIN,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_TAXI_FREE_AGAIN));
            } else {
                request.getSession().setAttribute(Constant.MESS_TAXI_IS_FREE,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_TAXI_IS_FREE));
            }
            if (tripService.isExistActiveTripByTaxiId(taxi.getId())) {
                tripService.changeInWayTripStatus(taxi.getId());
                request.getSession().setAttribute(Constant.MESS_TRIP_IS_FINISHED,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_TRIP_IS_FINISHED));
            }
            router = new Router(Constant.PATH_PAGE_TAXI_START_PAGE, Router.Type.FORWARD);
        } catch (LogicException e) {
            throw new CommandException("SetFreeWorkStatusCommandError ", e);
        }
        return router;
    }
}

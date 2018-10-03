package com.potapovich.project.command.taxi;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.Trip;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TripService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindHistoryOfDriverTripsCommand implements Command {

    private TripService tripService;

    public FindHistoryOfDriverTripsCommand(TripService tripService) {
        this.tripService = tripService;
    }

    /**
     * History of trips for a taxi driver
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        try {
            int driverId = (int) request.getSession().getAttribute(Constant.DRIVER_ID);
            List<Trip> listOfTrips = tripService.findTripsByDriverId(driverId);
            if (!listOfTrips.isEmpty()) {
                request.getSession().setAttribute(Constant.TRIPS_LIST, listOfTrips);
                router = new Router(Constant.PATH_PAGE_LIST_OF_DRIVER_TRIPS, Router.Type.FORWARD);
            } else {
                request.getSession().setAttribute(Constant.TRIP_LIST_IS_NOT_EXIST,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.TRIP_LIST_IS_NOT_EXIST));
                router = new Router(Constant.PATH_PAGE_TAXI_START_PAGE, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindHistoryOfUserTripsError ", e);
        }
        return router;
    }
}

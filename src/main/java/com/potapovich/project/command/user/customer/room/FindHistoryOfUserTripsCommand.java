package com.potapovich.project.command.user.customer.room;

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

public class FindHistoryOfUserTripsCommand implements Command {

    private TripService tripService;

    public FindHistoryOfUserTripsCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
            try {
                int customerId = (int) request.getSession().getAttribute(Constant.ID);
                List<Trip> listOfTrips;
                listOfTrips = tripService.findTripsByUserId(customerId);

                if (!listOfTrips.isEmpty()) {
                    request.getSession().setAttribute(Constant.TRIPS_LIST, listOfTrips);
                    router = new Router(Constant.PATH_PAGE_LIST_OF_USER_TRIPS, Router.Type.FORWARD);
                }
                else{
                    request.getSession().setAttribute(Constant.USER_TRIP_LIST_IS_NOT_EXIST,
                            new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                    getMessage(Constant.USER_TRIP_LIST_IS_NOT_EXIST));

                    router = new Router(Constant.PATH_PAGE_USER_ROOM, Router.Type.FORWARD);
                }
            } catch (LogicException e) {
                throw new CommandException("FindHistoryOfUserTripsError ", e);
            }

        return router;
    }
}

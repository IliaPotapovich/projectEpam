package com.potapovich.project.command.admin.find;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.entity.Trip;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TripService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class FindTripByIdCommand implements Command {

    private TripService tripService;

    public FindTripByIdCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        Trip trip;
        List<Trip> listOfTrips = new ArrayList<>();
        try {

                String id = request.getParameter(Constant.TRIP_FIND_ID);
                int tripId = Integer.parseInt(id);
                trip = tripService.findTripById(tripId);
                listOfTrips.add(trip);

            if (trip.getTripId()!=0){
                request.getSession().setAttribute(Constant.TRIPS_LIST, listOfTrips);
                router = new Router(Constant.PATH_PAGE_FIND_TRIPS, Router.Type.FORWARD);
            }
            else {
                request.getSession().setAttribute(Constant.TRIP_IS_NOT_EXIST_BY_THIS_ID,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.TRIP_IS_NOT_EXIST_BY_THIS_ID));

                router = new Router(Constant.PATH_PAGE_TRIP_FIND, Router.Type.FORWARD);
            }

        } catch (LogicException e) {
            throw new CommandException("FindDriverByIdCommandError ", e);
        }


        return router;

    }
}

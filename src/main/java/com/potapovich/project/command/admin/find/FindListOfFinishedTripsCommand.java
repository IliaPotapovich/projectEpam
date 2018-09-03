package com.potapovich.project.command.admin.find;

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

public class FindListOfFinishedTripsCommand implements Command {

    private TripService tripService;

    public FindListOfFinishedTripsCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        List<Trip> listOfTrips;
        try {
            listOfTrips = tripService.findListOfFinishedTrips();

            if (!listOfTrips.isEmpty()) {
                request.getSession().setAttribute(Constant.TRIPS_LIST, listOfTrips);
                router = new Router(Constant.PATH_PAGE_FIND_TRIPS, Router.Type.FORWARD);
            }
            else{
                request.getSession().setAttribute(Constant.FINISHED_TRIP_LIST_IS_NOT_EXIST,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.FINISHED_TRIP_LIST_IS_NOT_EXIST));

                router = new Router(Constant.PATH_PAGE_TRIP_FIND, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindListOfFinishedTripsCommandError ", e);
        }

        return router;
    }
}

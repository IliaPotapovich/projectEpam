package com.potapovich.project.command.user.common.order;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.logic.TripService;

import javax.servlet.http.HttpServletRequest;

public class ConfirmTripCommand implements Command {

   private TripService tripService;

    public ConfirmTripCommand(TripService tripService) {
        this.tripService = tripService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        int taxiId = (int) request.getSession().getAttribute(Constant.TAXI_ID);
        String customerPhone = (String) request.getSession().getAttribute(Constant.CUSTOMER_PHONE);
        String customerName = (String) request.getSession().getAttribute(Constant.CUSTOMER_NAME);
        double cost = 0;
        if (request.getSession().getAttribute(Constant.ROUTE_COST)!=null) {
             cost = (double) request.getSession().getAttribute(Constant.ROUTE_COST);
        }
        int tripId;
        int customerId = 0;
        if (request.getSession().getAttribute(Constant.ID)!=null){
            customerId = (int) request.getSession().getAttribute(Constant.ID);
            customerPhone = (String) request.getSession().getAttribute(Constant.PHONE);
            customerName = (String) request.getSession().getAttribute(Constant.NAME);
        }

        try {
        if (request.getSession().getAttribute(Constant.TRIP_ID)!=null){
            tripService.changeTrip((int)request.getSession().getAttribute(Constant.TRIP_ID),
                        taxiId, customerPhone, customerName, cost);
        }
        else {
            tripId = tripService.startTrip(customerId, taxiId, customerPhone, customerName, cost);
            request.getSession().setAttribute(Constant.TRIP_ID, tripId);
        }

        } catch (LogicException e) {
            throw new CommandException("ConfirmTripCommandError ", e);
        }
     return new Router(Constant.PATH_PAGE_CONFIRM_TRIP_END, Router.Type.FORWARD);



    }
}

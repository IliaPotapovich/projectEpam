package com.potapovich.project.command.user.common.order;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.logic.TripService;
import com.potapovich.project.logic.UserService;

import javax.servlet.http.HttpServletRequest;

public class TripEndCommand implements Command {

   private TripService tripService;
   private UserService userService;

    public TripEndCommand(TripService tripService, UserService userService) {
        this.tripService = tripService;
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException{

        Router router;
        int taxiId;

        try {
            if (request.getSession().getAttribute(Constant.TAXI_ID)!=null) {
                taxiId = (int) request.getSession().getAttribute(Constant.TAXI_ID);
            }
            else {
                return new Router(Constant.PATH_PAGE_END_TRIP, Router.Type.FORWARD);
            }

            int mark = 0;
            if (request.getSession().getAttribute(Constant.TAXI_MARK) != null) {
                String taxiMark = (String) request.getSession().getAttribute(Constant.TAXI_MARK);
                mark = Integer.parseInt(taxiMark);
            }
            if (request.getSession().getAttribute(Constant.ID) != null) {
                int customerId = (int) request.getSession().getAttribute(Constant.ID);
                userService.updateUserData(customerId);
            }
            tripService.finishTrip(taxiId, mark);
            router = new Router(Constant.PATH_PAGE_END_TRIP, Router.Type.FORWARD);

            int customerId= 0;
            String customerName="";
            String customerPhone="";
            int trip=0;
            int discount=0;

            if (request.getSession().getAttribute(Constant.ID) != null) {
                customerId = (int) request.getSession().getAttribute(Constant.ID);
                customerName = (String) request.getSession().getAttribute(Constant.NAME);
                customerPhone = (String) request.getSession().getAttribute(Constant.PHONE);
                trip = (int) request.getSession().getAttribute(Constant.TRIP);
                discount = (int) request.getSession().getAttribute(Constant.DISCOUNT);

            }
            String language = (String) request.getSession().getAttribute(Constant.LANGUAGE);

            request.getSession().invalidate();

            request.getSession().setAttribute(Constant.LANGUAGE, language);
            if (customerId != 0) {

                request.getSession().setAttribute(Constant.ID, customerId);
                request.getSession().setAttribute(Constant.NAME, customerName);
                request.getSession().setAttribute(Constant.PHONE, customerPhone);
                request.getSession().setAttribute(Constant.TRIP, trip);
                request.getSession().setAttribute(Constant.DISCOUNT, discount);
                request.getSession().setAttribute(Constant.ROLE, Constant.USER);
            }
        } catch (LogicException e) {
            throw new CommandException("TripEndCommandError ", e);
        }
        return router;
    }
}

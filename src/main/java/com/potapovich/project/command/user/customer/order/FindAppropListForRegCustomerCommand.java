package com.potapovich.project.command.user.customer.order;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.*;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;
import com.potapovich.project.logic.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindAppropListForRegCustomerCommand implements Command {

   private TaxiService taxiService;
   private UserService userService;


    public FindAppropListForRegCustomerCommand(TaxiService taxiService, UserService userService) {
        this.taxiService = taxiService;
        this.userService = userService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;

        try {
            Customer customer;
            String customerName = (String) request.getSession().getAttribute(Constant.NAME);
            String customerPhone = (String) request.getSession().getAttribute(Constant.PHONE);
            int fromCoordX = Integer.parseInt(request.getParameter(Constant.FROM_POINT_X));
            int fromCoordY = Integer.parseInt(request.getParameter(Constant.FROM_POINT_Y));
            int toCoordX;
            int toCoordY;

            if (!request.getParameter(Constant.TO_POINT_X).isEmpty() &&
                    !request.getParameter(Constant.TO_POINT_Y).isEmpty() ) {
                toCoordX = Integer.parseInt(request.getParameter(Constant.TO_POINT_X));
                toCoordY = Integer.parseInt(request.getParameter(Constant.TO_POINT_Y));
                request.getSession().setAttribute(Constant.TO_POINT_X, toCoordX);
                request.getSession().setAttribute(Constant.TO_POINT_Y, toCoordY);
            }

            request.getSession().setAttribute(Constant.FROM_POINT_X, fromCoordX);
            request.getSession().setAttribute(Constant.FROM_POINT_Y, fromCoordY);

            List<Taxi> listOfWorkingTaxi;

            listOfWorkingTaxi = taxiService.findListOfWorkingTaxi();

            if (!listOfWorkingTaxi.isEmpty()) {
                Route route = new Route(new Point(fromCoordX, fromCoordY));
                customer = new Customer(customerName, customerPhone, route);
                List<Taxi> appropriateTaxi = userService.callTaxi(customer, listOfWorkingTaxi);
                request.getSession().setAttribute(Constant.TAXI_APPROPRIATE_LIST, appropriateTaxi);
                request.getSession().setAttribute(Constant.MESS_TAXI_LIST_OF_APPROPRIATE_TAXI,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_TAXI_LIST_OF_APPROPRIATE_TAXI));
                router = new Router(Constant.PATH_PAGE_LIST_OF_APPROPRIATE_TAXI_FOR_REG, Router.Type.FORWARD);
            }

            else{
                listOfWorkingTaxi = null;
                request.getSession().setAttribute(Constant.TAXI_WORKING_LIST, listOfWorkingTaxi);
                router = new Router(Constant.PATH_PAGE_LIST_OF_WORKING_TAXI, Router.Type.FORWARD);
            }

            request.getSession().setAttribute(Constant.PREVIOUSS_PAGE, router.getPage());
        } catch (LogicException e) {
            throw new CommandException("FindAppropListForRegCustomerCommand ", e);
        }
        return router;
    }
}


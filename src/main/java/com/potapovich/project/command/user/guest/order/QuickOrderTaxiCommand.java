package com.potapovich.project.command.user.guest.order;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.*;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.logic.TaxiService;
import com.potapovich.project.logic.UserService;
import com.potapovich.project.validation.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class QuickOrderTaxiCommand implements Command {

    private UserService userService;
    private TaxiService taxiService;

    public QuickOrderTaxiCommand(UserService userService, TaxiService taxiService) {
        this.userService = userService;
        this.taxiService = taxiService;
    }

    /**
     * Quick calling a taxi without a taxi choice
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        try {
            if (request.getSession().getAttribute(Constant.TAXI_ID) != null) {
                taxiService.changeTaxiFreeStatusTrue((int) request.getSession().getAttribute(Constant.TAXI_ID));
            }
            String customerName = request.getParameter(Constant.CUSTOMER_NAME);
            String customerPhone = request.getParameter(Constant.CUSTOMER_PHONE);
            if (!DataValidator.validation(Constant.VALID_NAME, customerName) ||
                    !DataValidator.validation(Constant.VALID_PHONE, customerPhone) ||
                    !DataValidator.validation(Constant.VALID_NUMBER, request.getParameter(Constant.FROM_POINT_X),
                            request.getParameter(Constant.FROM_POINT_Y))){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            int fromCoordX = Integer.parseInt(request.getParameter(Constant.FROM_POINT_X));
            int fromCoordY = Integer.parseInt(request.getParameter(Constant.FROM_POINT_Y));
            request.getSession().setAttribute(Constant.CUSTOMER_NAME, customerName);
            request.getSession().setAttribute(Constant.CUSTOMER_PHONE, customerPhone);
            List<Taxi> listOfWorkingTaxi;
            if (request.getSession().getAttribute(Constant.TAXI_ID) != null) {
                taxiService.changeTaxiFreeStatusTrue((int) request.getSession().getAttribute(Constant.TAXI_ID));
            }
            listOfWorkingTaxi = taxiService.findListOfWorkingTaxi();
            if (!listOfWorkingTaxi.isEmpty()) {
                Route route = new Route(new Point(fromCoordX, fromCoordY));
                Customer customer = new Customer(customerName, customerPhone, route);
                Distance appropriateDistanceFromTaxiToClient = userService.findAppropriateDistanceToTaxi(customer, listOfWorkingTaxi);
                Taxi taxi = taxiService.findWorkingTaxiById(appropriateDistanceFromTaxiToClient.getId());
                int timeFromTaxiToClient = taxiService.calculateTimeByDistance(appropriateDistanceFromTaxiToClient);
                request.getSession().setAttribute(Constant.TAXI_ID, taxi.getId());
                request.getSession().setAttribute(Constant.DRIVER_NAME, taxi.getTaxiDriver().getDriverName());
                request.getSession().setAttribute(Constant.TIME_FROM_TAXI_TO_CLIENT, timeFromTaxiToClient);
                router = new Router(Constant.PATH_PAGE_QUICK_ORDER_CALCULATION, Router.Type.FORWARD);
                if (!request.getParameter(Constant.TO_POINT_X).isEmpty() && !request.getParameter(Constant.TO_POINT_Y).isEmpty()) {
                    if (!DataValidator.validation(Constant.VALID_NUMBER, request.getParameter(Constant.TO_POINT_X), request.getParameter(Constant.TO_POINT_Y))){
                        taxiService.changeTaxiFreeStatusTrue(taxi.getId());
                        return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
                    }
                    int toCoordX = Integer.parseInt(request.getParameter(Constant.TO_POINT_X));
                    int toCoordY = Integer.parseInt(request.getParameter(Constant.TO_POINT_Y));
                    Distance routeDistance = taxiService.findDistance(new Route(new Point(fromCoordX, fromCoordY),
                            new Point(toCoordX, toCoordY)));
                    int timeRoute = taxiService.calculateTimeByDistance(routeDistance);
                    double cost = taxiService.calculateCostByDistance(routeDistance);
                    request.getSession().setAttribute(Constant.ROUTE_DISTANCE, routeDistance.getDistance() / 10);
                    request.getSession().setAttribute(Constant.ROUTE_TIME, timeRoute);
                    request.getSession().setAttribute(Constant.ROUTE_COST, cost);
                    request.getSession().setAttribute(Constant.ROUTE_COST, cost);
                    request.getSession().setAttribute(Constant.CHOOSEN_TAXI_ID, taxi.getId());
                    router = new Router(Constant.PATH_PAGE_QUICK_ORDER_CALCULATION_WITH_CALCUL, Router.Type.FORWARD);
                }
            } else {
                listOfWorkingTaxi = null;
                request.getSession().setAttribute(Constant.TAXI_WORKING_LIST, listOfWorkingTaxi);
                router = new Router(Constant.PATH_PAGE_LIST_OF_WORKING_TAXI, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("QuickOrderTaxiCommandError ", e);
        }
        request.getSession().setAttribute(Constant.PREVIOUS_PAGE, router.getPage());
        return router;
    }
}

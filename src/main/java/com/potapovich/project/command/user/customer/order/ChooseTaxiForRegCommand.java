package com.potapovich.project.command.user.customer.order;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.*;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;
import com.potapovich.project.logic.UserService;
import com.potapovich.project.validation.DataValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.locks.ReentrantLock;

public class ChooseTaxiForRegCommand implements Command {

    private TaxiService taxiService;
    private UserService userService;

    public ChooseTaxiForRegCommand(TaxiService taxiService, UserService userService) {
        this.taxiService = taxiService;
        this.userService = userService;
    }

    /**
     * Choosing a certain taxi from the available list of taxi for registered users with subsequent calculation
     * @return Router with type FORWARD
     * @throws CommandException if LogicException
     */
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        try {
            ReentrantLock lock = new ReentrantLock();
            int customerId = (int) request.getSession().getAttribute(Constant.ID);
            int driverId = Integer.parseInt(request.getParameter(Constant.DRIVER_ID_CHOOSE));
            int fromCoordX = (int) request.getSession().getAttribute(Constant.FROM_POINT_X);
            int fromCoordY = (int) request.getSession().getAttribute(Constant.FROM_POINT_Y);
            int toCoordX;
            int toCoordY;
            Customer customer;
            if (request.getSession().getAttribute(Constant.TAXI_ID) != null) {
                taxiService.changeTaxiFreeStatusTrue((int) request.getSession().getAttribute(Constant.TAXI_ID));
            }
            if (request.getParameter(Constant.CHOOSE_IMAGE_ID) != null) {
                int imageId = Integer.parseInt(request.getParameter(Constant.CHOOSE_IMAGE_ID));
                request.getSession().setAttribute(Constant.CHOOSE_IMAGE_ID, imageId);
            }
            customer = userService.loadUser(customerId);
            router = new Router(Constant.PATH_PAGE_TRIP_CALCULATION_FOR_REG, Router.Type.FORWARD);
            if (request.getSession().getAttribute(Constant.TO_POINT_X) != null &&
                    request.getSession().getAttribute(Constant.TO_POINT_Y) != null) {
                toCoordX = (int) request.getSession().getAttribute(Constant.TO_POINT_X);
                toCoordY = (int) request.getSession().getAttribute(Constant.TO_POINT_Y);
                Distance routeDistance = taxiService.findDistance(new Route(new Point(fromCoordX, fromCoordY),
                        new Point(toCoordX, toCoordY)));
                int timeRoute = taxiService.calculateTimeByDistance(routeDistance);
                double cost = ((double) taxiService.calculateCostByDistance(routeDistance) / 100) * (100 - customer.getDiscountProcent());
                cost = Math.floor(cost);
                request.getSession().setAttribute(Constant.ROUTE_DISTANCE, routeDistance.getDistance() / 10);
                request.getSession().setAttribute(Constant.ROUTE_TIME, timeRoute);
                request.getSession().setAttribute(Constant.ROUTE_COST, cost);
                router = new Router(Constant.PATH_PAGE_TRIP_CALCULATION, Router.Type.FORWARD);
            }
            lock.lock();
            Taxi taxi = taxiService.findWorkingTaxiById(driverId);
            lock.unlock();
            Distance distanceFromTaxiToClient = taxiService.findDistance(new Route(new Point(taxi.getTaxiLocation().getX(),
                    taxi.getTaxiLocation().getY()), new Point(fromCoordX, fromCoordY)));
            int timeFromTaxiToClient = taxiService.calculateTimeByDistance(distanceFromTaxiToClient);
            request.getSession().setAttribute(Constant.TAXI_ID, taxi.getId());
            request.getSession().setAttribute(Constant.DRIVER_NAME, taxi.getTaxiDriver().getDriverName());
            request.getSession().setAttribute(Constant.TIME_FROM_TAXI_TO_CLIENT, timeFromTaxiToClient);
            request.getSession().setAttribute(Constant.DISCOUNT, customer.getDiscountProcent());
            request.getSession().setAttribute(Constant.MESS_DISCOUNT,
                    new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                            getMessage(Constant.MESS_DISCOUNT));
        } catch (LogicException e) {
            throw new CommandException("ChooseTaxiForRegCommandError ", e);
        }
        request.getSession().setAttribute(Constant.PREVIOUS_PAGE, router.getPage());
        return router;
    }
}

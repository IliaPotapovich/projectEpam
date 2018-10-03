package com.potapovich.project.command.user.guest.order;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.*;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.locks.ReentrantLock;

public class ChooseCertainTaxiCommand implements Command {

    private TaxiService taxiService;

    public ChooseCertainTaxiCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    /**
     * Selecting a certain taxi by an unregistered user
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
            ReentrantLock lock = new ReentrantLock();
            int driverId = Integer.parseInt(request.getParameter(Constant.DRIVER_ID_CHOOSE));
            int fromCoordX = (int) request.getSession().getAttribute(Constant.FROM_POINT_X);
            int fromCoordY = (int) request.getSession().getAttribute(Constant.FROM_POINT_Y);
            int toCoordX = (int) request.getSession().getAttribute(Constant.TO_POINT_X);
            int toCoordY = (int) request.getSession().getAttribute(Constant.TO_POINT_Y);
            Taxi taxi;
            if (request.getParameter(Constant.CHOOSE_IMAGE_ID) != null) {
                int imageId = Integer.parseInt(request.getParameter(Constant.CHOOSE_IMAGE_ID));
                request.getSession().setAttribute(Constant.CHOOSE_IMAGE_ID, imageId);
            }
            lock.lock();
            taxi = taxiService.findWorkingTaxiById(driverId);
            lock.unlock();
            Distance distanceFromTaxiToClient = taxiService.findDistance(new Route(new Point(taxi.getTaxiLocation().getX()
                    , taxi.getTaxiLocation().getY()), new Point(fromCoordX, fromCoordY)));
            Distance routeDistance = taxiService.findDistance(new Route(new Point(fromCoordX, fromCoordY),
                    new Point(toCoordX, toCoordY)));
            int timeFromTaxiToClient = taxiService.calculateTimeByDistance(distanceFromTaxiToClient);
            int timeRoute = taxiService.calculateTimeByDistance(routeDistance);
            double cost = taxiService.calculateCostByDistance(routeDistance);
            request.getSession().setAttribute(Constant.TAXI_ID, taxi.getId());
            request.getSession().setAttribute(Constant.DRIVER_NAME, taxi.getTaxiDriver().getDriverName());
            request.getSession().setAttribute(Constant.TIME_FROM_TAXI_TO_CLIENT, timeFromTaxiToClient);
            request.getSession().setAttribute(Constant.ROUTE_DISTANCE, routeDistance.getDistance() / 10);
            request.getSession().setAttribute(Constant.ROUTE_TIME, timeRoute);
            request.getSession().setAttribute(Constant.ROUTE_COST, cost);
            router = new Router(Constant.PATH_PAGE_TRIP_CALCULATION, Router.Type.FORWARD);
            request.getSession().setAttribute(Constant.PREVIOUS_PAGE, router.getPage());
        } catch (LogicException e) {
            throw new CommandException("ChooseCertainTaxiCommandError ", e);
        }
        return router;
    }
}

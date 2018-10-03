package com.potapovich.project.command.user.guest.order;

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
import java.util.ArrayList;
import java.util.List;

public class FindListOfAppropriateTaxiCommand implements Command {

    private TaxiService taxiService;
    private UserService userService;

    public FindListOfAppropriateTaxiCommand(TaxiService taxiService, UserService userService) {
        this.taxiService = taxiService;
        this.userService = userService;
    }

    /**
     * Find the nearest taxi list for an unregistered user
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
                            request.getParameter(Constant.FROM_POINT_Y), request.getParameter(Constant.TO_POINT_X),
                            request.getParameter(Constant.TO_POINT_Y))){
                return new Router(Constant.PATH_PAGE_START_PAGE, Router.Type.REDIRECT);
            }
            int fromCoordX = Integer.parseInt(request.getParameter(Constant.FROM_POINT_X));
            int fromCoordY = Integer.parseInt(request.getParameter(Constant.FROM_POINT_Y));
            int toCoordX = Integer.parseInt(request.getParameter(Constant.TO_POINT_X));
            int toCoordY = Integer.parseInt(request.getParameter(Constant.TO_POINT_Y));
            request.getSession().setAttribute(Constant.CUSTOMER_NAME, customerName);
            request.getSession().setAttribute(Constant.CUSTOMER_PHONE, customerPhone);
            request.getSession().setAttribute(Constant.FROM_POINT_X, fromCoordX);
            request.getSession().setAttribute(Constant.FROM_POINT_Y, fromCoordY);
            request.getSession().setAttribute(Constant.TO_POINT_X, toCoordX);
            request.getSession().setAttribute(Constant.TO_POINT_Y, toCoordY);
            List<Taxi> listOfWorkingTaxi;
            List<Taxi> appropriateTaxi = new ArrayList<>();
            listOfWorkingTaxi = taxiService.findListOfWorkingTaxi();
            if (!listOfWorkingTaxi.isEmpty()) {
                Route route = new Route(new Point(fromCoordX, fromCoordY), new Point(toCoordX, toCoordY));
                Customer customer = new Customer(customerName, customerPhone, route);
                appropriateTaxi = userService.callTaxi(customer, listOfWorkingTaxi);
            }
            if (!appropriateTaxi.isEmpty()) {
                request.getSession().setAttribute(Constant.TAXI_APPROPRIATE_LIST, appropriateTaxi);
                request.getSession().setAttribute(Constant.MESS_TAXI_LIST_OF_APPROPRIATE_TAXI,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.MESS_TAXI_LIST_OF_APPROPRIATE_TAXI));
                router = new Router(Constant.PATH_PAGE_LIST_OF_APPROPRIATE_TAXI, Router.Type.FORWARD);
            } else {
                listOfWorkingTaxi = null;
                request.getSession().setAttribute(Constant.TAXI_WORKING_LIST, listOfWorkingTaxi);
                router = new Router(Constant.PATH_PAGE_LIST_OF_WORKING_TAXI, Router.Type.FORWARD);
            }
            request.getSession().setAttribute(Constant.PREVIOUSS_PAGE, router.getPage());
        } catch (LogicException e) {
            throw new CommandException("FindListOfAppropriateTaxiCommandError ", e);
        }
        return router;
    }
}

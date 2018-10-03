package com.potapovich.project.filter;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Taxi;
import com.potapovich.project.entity.Trip;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;
import com.potapovich.project.logic.TripService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(urlPatterns = {"/controller"}, servletNames = {"Controller"})
public class InteruptedOrderDefinerFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void init(FilterConfig filterConfig) {

    }
    /**
     *  Redirects the user to the start page of the taxi order with special message in the event that the taxi driver refused or
     interrupted the order (for example, due to a long wait on the part of the user). Otherwise, the execution continues unchanged
     * forward into PATH_PAGE_ORDER_TAXI if the taxi driver interrupted the order
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        if (((HttpServletRequest) request).getSession().getAttribute(Constant.LANG_PAGE) != null) {
            String currentPage = (String) ((HttpServletRequest) request).getSession().getAttribute(Constant.LANG_PAGE);
            if ((currentPage.equals(Constant.PATH_PAGE_END_TRIP) ||
                    currentPage.equals(Constant.PATH_PAGE_QUICK_ORDER_CALCULATION) ||
                    currentPage.equals(Constant.PATH_PAGE_QUICK_ORDER_CALCULATION_WITH_CALCUL) ||
                    currentPage.equals(Constant.PATH_PAGE_TRIP_CALCULATION) ||
                    currentPage.equals(Constant.PATH_PAGE_TRIP_CALCULATION_FOR_REG) && session.getAttribute(Constant.ORDER) == null)) {
                try {
                    int taxiId;
                    Taxi taxi;
                    if (session.getAttribute(Constant.TAXI_ID) != null) {
                        TripService tripService = new TripService();
                        taxiId = (int) session.getAttribute(Constant.TAXI_ID);
                        taxi = new TaxiService().findTaxiById(taxiId);
                        Trip trip = tripService.findTripById(taxiId);
                        if (taxi.isFree()) {
                            if (trip.getTripId() != 0 && trip.getTaxi().getId() == taxiId && trip.isInWay()) {
                                tripService.changeInWayTripStatusOnFalse(taxi.getId());
                            }
                            ((HttpServletRequest) request).getSession().setAttribute(Constant.ORDER, "true");
                            httpServletRequest.getSession().setAttribute(Constant.MESS_INTERRUPT_ORDER,
                                    new MessageManager((String) httpServletRequest.getSession().getAttribute(Constant.LANGUAGE)).
                                            getMessage(Constant.MESS_INTERRUPT_ORDER));
                            ((HttpServletRequest) request).getSession().setAttribute(Constant.REDIRECTED_PAGE, Constant.PATH_PAGE_ORDER_TAXI);
                        }
                    }
                } catch (LogicException e) {
                    LOGGER.log(Level.ERROR, "InteruptedOrderDefinerFilterException ", e);
                    httpServletResponse.sendRedirect(Constant.PATH_PAGE_ERROR_PAGE);
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}

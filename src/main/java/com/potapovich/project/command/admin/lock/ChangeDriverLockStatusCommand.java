package com.potapovich.project.command.admin.lock;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ChangeDriverLockStatusCommand implements Command {

   private TaxiService taxiService;

    public ChangeDriverLockStatusCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        boolean updateStatus;
        try {
            String id = request.getParameter(Constant.TAXI_DRIVER_ID_CHOOSE);
            int driverId = Integer.parseInt(id);
            updateStatus = taxiService.updateLockStatus(driverId);

            if (request.getSession().getAttribute(Constant.DRIVER_LIST)!=null){
                List<TaxiDriver> listOfDrivers = taxiService.findListOfDriver();
                request.getSession().setAttribute(Constant.DRIVER_LIST, listOfDrivers);
                router = new Router(Constant.PATH_PAGE_LIST_OF_DRIVERS, Router.Type.FORWARD);
            }
            else {
                request.getSession().removeAttribute(Constant.DRIVER_STATUS);
                request.getSession().setAttribute(Constant.DRIVER_STATUS, updateStatus);
                router = new Router(Constant.PATH_PAGE_FIND_DRIVER, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("ChangeDriverLockStatusCommandError ", e);
        }
        return router;
    }

}

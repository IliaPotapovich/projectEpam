package com.potapovich.project.command.admin.find;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.entity.Trip;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class FindListOfDeletedDriversCommand implements Command {

    private TaxiService taxiService;

    public FindListOfDeletedDriversCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router;
        List<TaxiDriver> listOfDrivers;
        try {
            listOfDrivers = taxiService.findDeletedDriver();

            if (!listOfDrivers.isEmpty()) {
                request.getSession().setAttribute(Constant.DRIVER_LIST, listOfDrivers);
                router = new Router(Constant.PATH_PAGE_DELETED_DRIVERS, Router.Type.FORWARD);
            }
            else{
                request.getSession().setAttribute(Constant.DELETED_DRIVER_IS_NOT_EXIST,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.DELETED_DRIVER_IS_NOT_EXIST));

                router = new Router(Constant.PATH_PAGE_ARCHIVES, Router.Type.FORWARD);
            }
        } catch (LogicException e) {
            throw new CommandException("FindListOfDeletedDriversError ", e);
        }

        return router;
    }
}

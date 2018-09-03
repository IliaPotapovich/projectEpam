package com.potapovich.project.command.taxi;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.entity.Taxi;
import com.potapovich.project.entity.TaxiCar;
import com.potapovich.project.entity.TaxiDriver;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.IdGenerator;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;

public class DownloadAvatarCommand implements Command {
    private TaxiService taxiService;

    public DownloadAvatarCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {

        Router router;
        int imageId;

        if (request.getSession().getAttribute(Constant.IMAGE_ID)!=null) {
            imageId = (int) request.getSession().getAttribute(Constant.IMAGE_ID);
        }
        else {
            return new Router(Constant.PATH_PAGE_TAXI_START_PAGE, Router.Type.FORWARD);
        }
        String driverName = (String) request.getSession().getAttribute(Constant.TAXI_NAME);
        String driverPassword = String.valueOf(request.getSession().getAttribute(Constant.TAXI_PASS));
        String carId = (String) request.getSession().getAttribute(Constant.DESIRED_CAR_ID);
       int desiredCarId = Integer.parseInt(carId);
        try {

            if (taxiService.changeCarAvatar(imageId, driverName,driverPassword, desiredCarId)){

                    request.getSession().setAttribute(Constant.MESS_AVATAR_IS_CHANGED,
                            new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                    getMessage(Constant.MESS_AVATAR_IS_CHANGED));
                router = new Router(Constant.PATH_PAGE_TAXI_START_PAGE, Router.Type.FORWARD);
            }
            else {
                request.getSession().setAttribute(Constant.CAR_IS_NOT_EXIST,
                        new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                getMessage(Constant.CAR_IS_NOT_EXIST));
                router = new Router(Constant.PATH_PAGE_TAXI_ROOM, Router.Type.FORWARD);
            }

        } catch (LogicException e) {
            throw new CommandException("DownloadAvatarCommandError ", e);
        }
        return router;
    }
}

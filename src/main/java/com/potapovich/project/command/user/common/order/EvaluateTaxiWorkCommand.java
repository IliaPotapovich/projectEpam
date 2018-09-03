package com.potapovich.project.command.user.common.order;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.Router;
import com.potapovich.project.localization.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class EvaluateTaxiWorkCommand implements Command {


        @Override
        public Router execute(HttpServletRequest request) {

            request.getSession().setAttribute(Constant.TAXI_MARK, request.getParameter(Constant.MARK));

            request.getSession().setAttribute(Constant.MESS_THANKS_FOR_EVALUATE,
                    new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                            getMessage(Constant.MESS_THANKS_FOR_EVALUATE));
            return new Router(Constant.PATH_PAGE_CONFIRM_TRIP_END, Router.Type.FORWARD);
        }

}

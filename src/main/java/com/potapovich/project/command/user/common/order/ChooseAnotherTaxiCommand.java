package com.potapovich.project.command.user.common.order;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.command.Command;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;

import javax.servlet.http.HttpServletRequest;

public class ChooseAnotherTaxiCommand implements Command {
    private TaxiService taxiService;

    public ChooseAnotherTaxiCommand(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @Override
    public Router execute(HttpServletRequest request) throws CommandException{
        Router router;

        try {
            int taxiId = (int) request.getSession().getAttribute(Constant.TAXI_ID);
            taxiService.changeTaxiFreeStatusTrue(taxiId);

            router = new Router((String) request.getSession().getAttribute(Constant.PREVIOUSS_PAGE), Router.Type.FORWARD);
            request.getSession().setAttribute(Constant.MESS_CHOOSE_ANOTHER_TAXI,
                    new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                            getMessage(Constant.MESS_CHOOSE_ANOTHER_TAXI));
        } catch (LogicException e) {
            throw new CommandException("ChooseAnotherTaxiCommandError ", e);
        }
        return router;
    }
}

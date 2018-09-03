package com.potapovich.project.command.admin.lock;

import com.potapovich.project.command.Command;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.localization.MessageManager;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

public class DeletePreviousSiteDesign implements Command {
    private static final String UPLOAD_DIR = "background";
    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        File fileSaveDir = new File(uploadFilePath);
        if(fileSaveDir.exists()){
            for (File file : fileSaveDir.listFiles()) {
                file.delete();
            }
            fileSaveDir.delete();
            request.getSession().setAttribute(Constant.MESS_DELETE_PREVIOUS_DESIGN,
                    new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                            getMessage(Constant.MESS_DELETE_PREVIOUS_DESIGN));
        }
        else{
            request.getSession().setAttribute(Constant.MESS_DELETE_PREVIOUS_DESIGN_DOES_NOT_EXIST,
                    new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                            getMessage(Constant.MESS_DELETE_PREVIOUS_DESIGN_DOES_NOT_EXIST));
        }
        return new Router(Constant.PATH_PAGE_SITE_DESIGN, Router.Type.FORWARD);
    }
}

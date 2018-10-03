package com.potapovich.project.controller;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.TaxiService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

/**
 * Servlet for uploading personal photos of taxi cars
 * Forward to TaxiRoom
 */
@WebServlet(urlPatterns = "/uploadAvatar")
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)

public class AvatarDownloader extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String UPLOAD_DIR = "avatar";
    private int lastCarImageId = 0;

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        try {
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
            File fileSaveDir = new File(uploadFilePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }
            for (Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null && part.getSize() != 0) {
                    TaxiService taxiService = new TaxiService();
                    lastCarImageId = taxiService.findLastImageCarId();
                    part.write(uploadFilePath + File.separator + ++lastCarImageId + ".jpg");
                    request.getSession().setAttribute(Constant.IMAGE_ID, lastCarImageId);
                    request.getSession().setAttribute(Constant.MESS_PHOTO_WAS_UPLOAD,
                            new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                    getMessage(Constant.MESS_PHOTO_WAS_UPLOAD));
                }
            }
            request.getSession().setAttribute(Constant.LANG_PAGE, Constant.PATH_PAGE_TAXI_ROOM);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.PATH_PAGE_TAXI_ROOM);
            dispatcher.forward(request, response);
        } catch (LogicException e) {
            LOGGER.log(Level.ERROR, "AvatarDownloaderException ", e);
        }
    }
}

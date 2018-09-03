package com.potapovich.project.controller;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.exception.LogicException;
import com.potapovich.project.localization.MessageManager;
import com.potapovich.project.logic.IdGenerator;
import com.potapovich.project.logic.TaxiService;

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


@WebServlet(urlPatterns = "/uploadAvatar")
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)

public class AvatarDownloader extends HttpServlet {

        private static final String UPLOAD_DIR = "avatar";
    private int lastCarImageId = 0;
        @Override
        protected void doPost(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
            try {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");


            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;


            File fileSaveDir = new File(uploadFilePath);
            if(!fileSaveDir.exists()){
                fileSaveDir.mkdirs();
            }
            response.getWriter().println("Upload File Directory = "+fileSaveDir.getAbsolutePath());

            for(Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null && part.getSize()!=0) {

                    TaxiService taxiService = new TaxiService();

                        lastCarImageId = taxiService.findLastImageCarId();

                    part.write(uploadFilePath + File.separator + ++lastCarImageId +".jpg");
                    request.getSession().setAttribute(Constant.IMAGE_ID,lastCarImageId);


                    request.getSession().setAttribute(Constant.MESS_PHOTO_WAS_UPLOAD,
                            new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                    getMessage(Constant.MESS_PHOTO_WAS_UPLOAD));
                }
            }
                request.getSession().setAttribute(Constant.LANG_PAGE, Constant.PATH_PAGE_TAXI_ROOM);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.PATH_PAGE_TAXI_ROOM);
            dispatcher.forward(request,response);

            } catch (LogicException e) {
                e.printStackTrace();
            }
        }
}

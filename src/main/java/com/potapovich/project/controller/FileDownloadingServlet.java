package com.potapovich.project.controller;

import com.potapovich.project.constant.Constant;
import com.potapovich.project.localization.MessageManager;

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


@WebServlet(urlPatterns = "/uploadBackground")
@MultipartConfig(fileSizeThreshold = 1024 * 1024
        , maxFileSize = 1024 * 1024 * 5
        , maxRequestSize = 1024 * 1024 * 5 * 5)


public class FileDownloadingServlet extends HttpServlet {

        private static final String UPLOAD_DIR = "background";
        @Override
        protected void doPost(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException {
            String applicationPath = request.getServletContext().getRealPath("");
            String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

            File fileSaveDir = new File(uploadFilePath);
            if(!fileSaveDir.exists()){
                fileSaveDir.mkdirs();
            }

            for(Part part : request.getParts()) {
                if (part.getSubmittedFileName() != null && part.getSize()!=0) {
                    part.write(uploadFilePath + File.separator + part.getSubmittedFileName());
                    request.getSession().setAttribute(Constant.MESS_BACKGROUND_WAS_UPLOAD,
                            new MessageManager((String) request.getSession().getAttribute(Constant.LANGUAGE)).
                                    getMessage(Constant.MESS_BACKGROUND_WAS_UPLOAD));
                }
            }
            request.getSession().setAttribute(Constant.LANG_PAGE, Constant.PATH_PAGE_ADMIN_START_PAGE);
            RequestDispatcher dispatcher = request.getRequestDispatcher(Constant.PATH_PAGE_ADMIN_START_PAGE);
            dispatcher.forward(request,response);
        }

    }






package com.potapovich.project.controller;

import com.potapovich.project.command.CommandFactory;
import com.potapovich.project.command.Command;
import com.potapovich.project.command.EmptyCommand;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.dao.AdminDaoImpl;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.pool.ConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@WebServlet(urlPatterns = "/controller")
public class Controller extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {

        AdminDaoImpl adminDao = new AdminDaoImpl();
        try {
            adminDao.createStandartAdmin();
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().releasePool();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");

            Optional<Command> commandOptional = CommandFactory.defineCommand(req.getParameter("command"));
            Command command = commandOptional.orElse(new EmptyCommand());
             Router router = command.execute(req);
            req.getSession().setAttribute(Constant.LANG_PAGE, router.getPage());

            if (router.getPage() == null){
                resp.sendRedirect("jsp/errorpage.jsp");
            }

            if ( Router.Type.FORWARD.equals(router.getType())){
                RequestDispatcher dispatcher = req.getRequestDispatcher(router.getPage());
                dispatcher.forward(req,resp);

            }
            else {
                resp.sendRedirect(router.getPage());
            }

        } catch (CommandException e) {
            e.printStackTrace();
            resp.sendRedirect("jsp/errorpage.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }






}

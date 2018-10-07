package com.potapovich.project.controller;

import com.potapovich.project.command.Command;
import com.potapovich.project.command.CommandFactory;
import com.potapovich.project.command.EmptyCommand;
import com.potapovich.project.constant.Constant;
import com.potapovich.project.dao.AdminDaoImpl;
import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;
import com.potapovich.project.exception.DaoException;
import com.potapovich.project.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * The main servlet for handling responses and requests using the Command pattern
 * Forward or Redirect to the Router.page
 */
@WebServlet(urlPatterns = "/controller")
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();


    @Override
    public void destroy() {
        ConnectionPool.getInstance().releasePool();
    }

    /**
     At the time when the transition is carried out using a redirect, PageSecurityFilter catches this
     transition by perceiving it as a direct jump to the jsp page. The attribute REDIRECTED_PAGE saves
     the address of the page address to which we need to go, because when switching using the redirect,
     we lose this address with the creation of a new request
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Optional<Command> commandOptional = CommandFactory.defineCommand(req.getParameter("command"));
            Command command = commandOptional.orElse(new EmptyCommand());
            Router router = command.execute(req);
            req.getSession().setAttribute(Constant.LANG_PAGE, router.getPage());
            LOGGER.log(Level.INFO, "Current command " + command);
            if (Router.Type.FORWARD.equals(router.getType())) {
                if (req.getSession().getAttribute(Constant.REDIRECTED_PAGE) != null) {
                    router.setPage((String) req.getSession().getAttribute(Constant.REDIRECTED_PAGE));
                    req.getSession().removeAttribute(Constant.REDIRECTED_PAGE);
                }
                RequestDispatcher dispatcher = req.getRequestDispatcher(router.getPage());
                dispatcher.forward(req, resp);
        } else {
            req.getSession().setAttribute(Constant.REDIRECTED_PAGE, "/" + router.getPage());
            resp.sendRedirect(router.getPage());
        }
    } catch (CommandException e) {
            LOGGER.log(Level.ERROR, "Controller Exception ", e);
        resp.sendRedirect(Constant.PATH_PAGE_ERROR_PAGE);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}

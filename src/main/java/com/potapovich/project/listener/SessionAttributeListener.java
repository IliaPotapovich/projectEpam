package com.potapovich.project.listener;

import com.potapovich.project.constant.Constant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebServlet(urlPatterns = "/listener")

public class SessionAttributeListener implements HttpSessionAttributeListener{

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        HttpSession session = event.getSession();

        if (event.getName().equals(Constant.LANGUAGE) || event.getName().equals(Constant.LANG_PAGE)
                && !session.isNew())
        {
            if (!event.getValue().equals(Constant.PATH_PAGE_REGISTER) &&
                    !event.getValue().equals(Constant.PATH_PAGE_LOGIN) &&
                    !event.getValue().equals(Constant.PATH_PAGE_TECHNICAL_OFFICE)) {
                session.removeAttribute(Constant.REG_SUCCESS);
                session.removeAttribute(Constant.ERROR_LOGIN_PASS_MESSAGE);
                session.removeAttribute(Constant.MESS_YOU_ARE_BLOCKED);
            }
            if (!event.getValue().equals(Constant.PATH_PAGE_ARCHIVES)) {
                session.removeAttribute(Constant.DELETED_DRIVER_IS_NOT_EXIST);
                session.removeAttribute(Constant.DELETED_CARS_IS_NOT_EXIST);
            }

            if (!event.getValue().equals(Constant.PATH_PAGE_ADMIN_START_PAGE)
                    && !event.getValue().equals(Constant.PATH_PAGE_ADMIN_REGISTER)) {
                 session.removeAttribute(Constant.REGISTR_ERROR);
            }

            if (!event.getValue().equals(Constant.PATH_PAGE_ADMIN_START_PAGE.substring(1))&&
                    !event.getValue().equals("/jsp/taxi_car_register.jsp")&&
                    !event.getValue().equals("/jsp/taxi_driver_register.jsp")){
                session.removeAttribute(Constant.REG_TAXI_SUCCESS);
                session.removeAttribute(Constant.REG_CAR_SUCCESS);
            }
            if (!event.getValue().equals(Constant.PATH_PAGE_CHANGE_ADMIN_PASSWORD) &&
                    !event.getValue().equals(Constant.PATH_PAGE_CHANGE_DRIVER_PASSWORD) &&
                    !event.getValue().equals(Constant.PATH_PAGE_CHANGE_USER_PASSWORD) &&
                    !event.getValue().equals(Constant.PATH_PAGE_USER_ROOM) &&
                    !event.getValue().equals(Constant.PATH_PAGE_TAXI_START_PAGE) &&
                    !event.getValue().equals(Constant.PATH_PAGE_LIST_OF_ADMINS)) {
                session.removeAttribute(Constant.LOGIN_ERROR);
                session.removeAttribute(Constant.MESS_PASS_CHANGED);
            }

             if (!event.getValue().equals(Constant.PATH_PAGE_CONFIRM_TRIP_END)) {
                session.removeAttribute(Constant.MESS_THANKS_FOR_EVALUATE);
                session.removeAttribute(Constant.MESS_JUST_TRIP_END);
            }
            if (!event.getValue().equals(Constant.PATH_PAGE_SITE_DESIGN) &&
                    !event.getValue().equals(Constant.PATH_PAGE_ADMIN_START_PAGE)) {
                session.removeAttribute(Constant.MESS_DELETE_PREVIOUS_DESIGN);
                session.removeAttribute(Constant.MESS_DELETE_PREVIOUS_DESIGN_DOES_NOT_EXIST);
                session.removeAttribute(Constant.MESS_BACKGROUND_WAS_UPLOAD);
            }
            if (!event.getValue().equals(Constant.PATH_PAGE_ADMIN_START_PAGE)
                    && !event.getValue().equals(Constant.PATH_PAGE_USER_ROOM) &&
                    !event.getValue().equals(Constant.PATH_PAGE_START_PAGE) &&
                    !event.getValue().equals(Constant.PATH_PAGE_TAXI_START_PAGE)) {
                session.removeAttribute(Constant.LOGOUT_MESSAGE);
            }
            if (!event.getValue().equals(Constant.PATH_PAGE_TAXI_FIND) &&
                    !event.getValue().equals(Constant.PATH_PAGE_TAXI_START_PAGE)) {
                session.removeAttribute(Constant.DRIVER_ERROR_MESSAGE);
                session.removeAttribute(Constant.DRIVER_NAME_ERROR_MESSAGE);
                session.removeAttribute(Constant.CAR_ID_ERROR_MESSAGE);
                session.removeAttribute(Constant.CAR_OWNER_ID_ERROR_MESSAGE);
                session.removeAttribute(Constant.DRIVER_IS_NOT_EXIST);
                session.removeAttribute(Constant.MESS_DELETE_SUCCESS);
                session.removeAttribute(Constant.CAR_IS_NOT_EXIST);
                session.removeAttribute(Constant.DRIVER_LIST_IS_NOT_EXIST);
                session.removeAttribute(Constant.СAR_LIST_IS_NOT_EXIST);
            }

            if (!event.getValue().equals(Constant.PATH_PAGE_TAXI_START_PAGE) &&
                    !event.getValue().equals(Constant.PATH_PAGE_TAXI_ROOM) &&
                    !event.getValue().equals(Constant.PATH_PAGE_TRIP_FIND) &&
                    !event.getValue().equals(Constant.PATH_PAGE_USER_ROOM)) {
                session.removeAttribute(Constant.CAR_ID_ERROR_MESSAGE);
                session.removeAttribute(Constant.REG_DRIVER_EXIST);
                session.removeAttribute(Constant.MESS_AVATAR_IS_CHANGED);
                session.removeAttribute(Constant.TRIP_LIST_IS_NOT_EXIST);
                session.removeAttribute(Constant.MESS_DRIVER_FINISH_WORK);
                session.removeAttribute(Constant.MESS_DRIVER_WORK_WAS_NOT_STARTED);
            }

            if (!event.getValue().equals(Constant.PATH_PAGE_TECHNICAL_OFFICE)) {
                session.removeAttribute(Constant.MESS_YOU_ARE_BLOCKED);
                session.removeAttribute(Constant.ERROR_TECHN_LOGIN_PASS_MESSAGE);

            }

            if (!event.getValue().equals(Constant.PATH_PAGE_TRIP_FIND)) {
                session.removeAttribute(Constant.TRIP_IS_NOT_EXIST_BY_THIS_ID);
                session.removeAttribute(Constant.TAXI_IS_NOT_EXIST_BY_THIS_ID);
                session.removeAttribute(Constant.TRIP_LIST_IS_NOT_EXIST);
                session.removeAttribute(Constant.ACTIVE_TRIP_LIST_IS_NOT_EXIST);
                session.removeAttribute(Constant.FINISHED_TRIP_LIST_IS_NOT_EXIST);
            }

            if (!event.getValue().equals(Constant.PATH_PAGE_USER_FIND)) {
                session.removeAttribute(Constant.MESS_LOGIN_IS_NOT_EXIST);
                session.removeAttribute(Constant.MESS_ID_IS_NOT_EXIST);
                session.removeAttribute(Constant.USERS_NOT_EXIST);
            }

            if (!event.getValue().equals(Constant.PATH_PAGE_USER_ROOM)) {
                session.removeAttribute(Constant.USER_TRIP_LIST_IS_NOT_EXIST);
            }

        }

        System.out.println("[SessionAttr] "+new java.util.Date()+" Attribute changed, session "+session+": "+event.getName()+"="+event.getValue());

    }




}

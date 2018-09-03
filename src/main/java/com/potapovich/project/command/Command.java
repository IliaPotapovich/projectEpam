package com.potapovich.project.command;

import com.potapovich.project.entity.Router;
import com.potapovich.project.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    Router execute(HttpServletRequest request) throws CommandException;


}

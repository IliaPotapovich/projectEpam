package com.potapovich.project.command;

import com.potapovich.project.exception.CommandException;

import java.util.Optional;

public class CommandFactory {


    public static Optional<Command> defineCommand(String commandName) throws CommandException{

    Optional<Command> current = Optional.empty();
    if (commandName == null){
        return current;
    }

    try {
        CommandType type = CommandType.valueOf(commandName.toUpperCase());

        current = Optional.of(type.getCommand());
    } catch (IllegalArgumentException e) {
        throw new CommandException("CommandFactory ", e);
    }

    return current;

}
}

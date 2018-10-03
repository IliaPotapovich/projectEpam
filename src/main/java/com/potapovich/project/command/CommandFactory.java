package com.potapovich.project.command;

import com.potapovich.project.exception.CommandException;

import java.util.Optional;

public class CommandFactory {
    /**
     * The definition of the name of a certain command and its subsequent execution
     * @return name of command
     * @throws CommandException if IllegalArgumentException
     */
    public static Optional<Command> defineCommand(String commandName) throws CommandException {
        Optional<Command> current = Optional.empty();
        try {
            if (commandName == null) {
                return current;
            }
            CommandType type = CommandType.valueOf(commandName.toUpperCase());
            current = Optional.of(type.getCommand());
        } catch (IllegalArgumentException e) {
            throw new CommandException("CommandFactory ", e);
        }
        return current;
    }
}

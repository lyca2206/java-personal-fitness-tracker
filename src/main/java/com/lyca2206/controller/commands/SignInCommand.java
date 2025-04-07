package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;

import java.util.Collection;

public class SignInCommand extends Command {
    private final CommandsFactory commandsFactory;

    public SignInCommand(CommandProcessor processor, String key, String information, CommandsFactory commandsFactory) {
        super(processor, key, information);
        this.commandsFactory = commandsFactory;
    }

    @Override
    public void execute(String[] tokens) {
        //TODO. Write Sign In Logic!
        Collection<Command> commands = commandsFactory.createCommands(""); //TODO. Change key!
        processor.changeCommands(commands);
    }
}
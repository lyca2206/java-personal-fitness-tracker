package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.libraries.session.Session;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;

import java.util.Collection;

public class SignOutCommand extends Command {
    private final CommandsFactory commandsFactory;

    public SignOutCommand(CommandProcessor processor, String key, String information, CommandsFactory commandsFactory) {
        super(processor, key, information);
        this.commandsFactory = commandsFactory;
    }

    @Override
    public void execute(String[] tokens) {
        Collection<Command> commands = commandsFactory.createCommands("SIGNED_OUT");
        processor.changeCommands(commands);

        Session.getInstance().setPrincipal(null);

        System.out.println("Signed out successfully");
    }
}
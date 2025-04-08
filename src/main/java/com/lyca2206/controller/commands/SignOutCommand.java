package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.libraries.session.Session;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;

public class SignOutCommand extends Command {
    private final CommandsFactory commandsFactory;

    public SignOutCommand(
            CommandProcessor processor,
            String key,
            String information,
            CommandsFactory commandsFactory
    ) {
        super(processor, key, information);
        this.commandsFactory = commandsFactory;
    }

    @Override
    public void execute(String[] tokens) {
        changeCommandsToSignedOut();
        Session.getInstance().setPrincipal(null);
        System.out.println("\nYou have signed out of the application successfully\n");
    }

    private void changeCommandsToSignedOut() {
        processor.changeCommands(
                commandsFactory.createCommands("SIGNED_OUT")
        );
    }
}
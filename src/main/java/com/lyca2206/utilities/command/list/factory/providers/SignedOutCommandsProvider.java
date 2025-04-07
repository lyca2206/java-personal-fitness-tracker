package com.lyca2206.utilities.command.list.factory.providers;

import com.lyca2206.controller.application.Application;
import com.lyca2206.controller.commands.ExitCommand;
import com.lyca2206.controller.commands.SignInCommand;
import com.lyca2206.controller.commands.SignUpCommand;
import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;
import com.lyca2206.utilities.command.list.factory.CommandsProvider;

import java.util.Collection;
import java.util.List;

public class SignedOutCommandsProvider extends CommandsProvider {
    private final Application application;

    public SignedOutCommandsProvider(CommandsFactory commandsFactory, CommandProcessor processor, String key, Application application) {
        super(commandsFactory, processor, key);
        this.application = application;
    }

    @Override
    public Collection<Command> createCommands() {
        return List.of(
                new ExitCommand(
                        processor,
                        "exit",
                        "exit - Exits the application",
                        application
                ),

                new SignInCommand(
                        processor,
                        "signIn",
                        "signIn - Prompts sign in form, asking for username and password"
                ),

                new SignUpCommand(
                        processor,
                        "signUp",
                        "signUp - Prompts sign up form, asking for various details of the user"
                )
        );
    }
}
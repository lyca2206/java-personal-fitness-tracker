package com.lyca2206.utilities.command.list.factory.providers;

import com.lyca2206.controller.application.Application;
import com.lyca2206.controller.commands.ExitCommand;
import com.lyca2206.controller.commands.SignInCommand;
import com.lyca2206.controller.commands.SignUpCommand;
import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.repository.abstraction.AuthenticationRepository;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;
import com.lyca2206.utilities.command.list.factory.CommandsProvider;

import java.util.Collection;
import java.util.List;

public class SignedOutCommandsProvider extends CommandsProvider {
    private final Application application;
    private final AuthenticationRepository repository;

    public SignedOutCommandsProvider(CommandsFactory commandsFactory, CommandProcessor processor, String key, Application application, AuthenticationRepository repository) {
        super(commandsFactory, processor, key);
        this.application = application;
        this.repository = repository;
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
                        "signIn [email] [password] - Tries to sign in as the given user",
                        commandsFactory
                ),

                new SignUpCommand(
                        processor,
                        "signUp",
                        "signUp [email] [password] [role] [firstName] [lastName] - Creates a new user in the system using the given parameters",
                        repository
                )
        );
    }
}
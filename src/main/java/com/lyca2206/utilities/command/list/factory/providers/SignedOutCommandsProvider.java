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
    private final AuthenticationRepository authenticationRepository;

    public SignedOutCommandsProvider(
            CommandsFactory commandsFactory,
            CommandProcessor processor,
            String key,
            Application application,
            AuthenticationRepository authenticationRepository
    ) {
        super(commandsFactory, processor, key);
        this.application = application;
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public Collection<Command> createCommands() {
        return List.of(
                new SignInCommand(
                        processor,
                        "SignIn",
                        "SignIn <Email : Text> <Password : Text> : Signs In as the specified user if the credentials are valid",
                        commandsFactory, authenticationRepository
                ),

                new SignUpCommand(
                        processor,
                        "SignUp",
                        "SignUp <Email : Text> <Password : Text> <Role : REGULAR | ADMIN> <FirstName : Text> <LastName : Text> : Signs Up in the application with the provided information",
                        authenticationRepository
                ),

                new ExitCommand(
                        processor,
                        "Exit",
                        "Exit : Exits the application",
                        application
                )
        );
    }
}
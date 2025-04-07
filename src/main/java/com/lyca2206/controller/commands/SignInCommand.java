package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.AuthenticationRepository;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;

import java.util.Collection;

public class SignInCommand extends Command {
    private final AuthenticationRepository authenticationRepository;
    private final CommandsFactory commandsFactory;

    public SignInCommand(CommandProcessor processor, String key, String information, AuthenticationRepository authenticationRepository, CommandsFactory commandsFactory) {
        super(processor, key, information);
        this.authenticationRepository = authenticationRepository;
        this.commandsFactory = commandsFactory;
    }

    @Override
    public void execute(String[] tokens) {
        try {

            User user = new User(
                    tokens[0],
                    tokens[1]
            );

            User storedUser = authenticationRepository.signIn(user);

            Collection<Command> commands = commandsFactory.createCommands(storedUser.getRole().name());
            processor.changeCommands(commands);

            System.out.println("Signed in successfully");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not enough information given for authentication");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.libraries.session.Session;
import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.AuthenticationRepository;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;

public class SignInCommand extends Command {
    private final CommandsFactory commandsFactory;
    private final AuthenticationRepository authenticationRepository;

    public SignInCommand(CommandProcessor processor, String key, String information, CommandsFactory commandsFactory, AuthenticationRepository authenticationRepository) {
        super(processor, key, information);
        this.commandsFactory = commandsFactory;
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public void execute(String[] tokens) {
        try {

            User user = createUserInstance(tokens);
            User storedUser = authenticationRepository.signIn(user);

            changeCommandsWithUserRole(storedUser);
            Session.getInstance().setPrincipal(storedUser);

            System.out.println("\nYou have signed into the application successfully\n");

        } catch (IndexOutOfBoundsException e) {

            System.out.println("\nThe provided information isn't enough to proceed with authentication\n");

        } catch (Exception e) {

            System.out.println("\n" + e.getMessage() + "\n");

        }
    }

    private User createUserInstance(String[] tokens) {
        String email = tokens[0];
        String password = tokens[1];

        return new User(email, password);
    }

    private void changeCommandsWithUserRole(User storedUser) {
        processor.changeCommands(
                commandsFactory.createCommands(storedUser.getRole().name())
        );
    }
}
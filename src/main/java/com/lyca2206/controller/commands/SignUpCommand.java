package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.Role;
import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.AuthenticationRepository;

public class SignUpCommand extends Command {
    private final AuthenticationRepository authenticationRepository;

    public SignUpCommand(
            CommandProcessor processor,
            String key,
            String information,
            AuthenticationRepository authenticationRepository
    ) {
        super(processor, key, information);
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public void execute(String[] tokens) {
        try {

            User user = createUserInstance(tokens);
            authenticationRepository.signUp(user);
            System.out.println("\nThe user has been created successfully\n");

        } catch (IndexOutOfBoundsException e) {

            System.out.println("\nThe provided information is not enough to create a new user\n");

        } catch (Exception e) {

            System.out.println("\n" + e.getMessage() + "\n");

        }
    }

    private User createUserInstance(String[] tokens) {
        String email = tokens[0];
        String password = tokens[1];
        Role role = Role.valueOf(tokens[2].toUpperCase());
        String firstName = tokens[3];
        String lastName = tokens[4];

        return new User(email, password, role, firstName, lastName);
    }
}
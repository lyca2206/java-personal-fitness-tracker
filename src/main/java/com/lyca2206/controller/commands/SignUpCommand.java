package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.Role;
import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.AuthenticationRepository;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class SignUpCommand extends Command {
    private final AuthenticationRepository authenticationRepository;

    public SignUpCommand(CommandProcessor processor, String key, String information, AuthenticationRepository authenticationRepository) {
        super(processor, key, information);
        this.authenticationRepository = authenticationRepository;
    }

    @Override
    public void execute(String[] tokens) {
        try {
            User user = new User(
                    tokens[0],
                    tokens[1],
                    Role.valueOf(tokens[2]),
                    tokens[3],
                    tokens[4]
            );

            authenticationRepository.signUp(user);

            System.out.println("User created successfully");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not enough information to create the user");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.out.println("The user couldn't be created");
        }
    }
}
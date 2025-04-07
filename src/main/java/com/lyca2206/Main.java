package com.lyca2206;

import com.lyca2206.controller.application.Application;
import com.lyca2206.controller.application.CommandLineApplication;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.libraries.command.processor.MapCommandProcessor;
import com.lyca2206.model.Role;
import com.lyca2206.repository.abstraction.AuthenticationRepository;
import com.lyca2206.repository.abstraction.ExerciseRepository;
import com.lyca2206.repository.implementation.InMemoryAuthentication;
import com.lyca2206.repository.implementation.InMemoryExercise;
import com.lyca2206.utilities.command.list.factory.CommandsProvidersFactory;
import com.lyca2206.utilities.command.list.factory.providers.AdminCommandsProvider;
import com.lyca2206.utilities.command.list.factory.providers.RegularCommandsProvider;
import com.lyca2206.utilities.command.list.factory.providers.SignedOutCommandsProvider;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Reader reader = new InputStreamReader(System.in);
        CommandProcessor processor = new MapCommandProcessor(new HashMap<>());
        Application application = new CommandLineApplication(reader, processor);
        CommandsProvidersFactory providersFactory = new CommandsProvidersFactory(new HashSet<>());
        AuthenticationRepository authenticationRepository = new InMemoryAuthentication(new HashMap<>());
        ExerciseRepository exerciseRepository = new InMemoryExercise(new HashMap<>());

        providersFactory.setProviders(List.of(
                new SignedOutCommandsProvider(
                        providersFactory,
                        processor,
                        "SIGNED_OUT",
                        application,
                        authenticationRepository
                ),

                new AdminCommandsProvider(
                        providersFactory,
                        processor,
                        Role.ADMIN.name(),
                        exerciseRepository
                ),

                new RegularCommandsProvider(
                        providersFactory,
                        processor,
                        Role.REGULAR.name()
                )
        ));

        processor.changeCommands(providersFactory.createCommands("SIGNED_OUT"));
        application.run();
    }
}
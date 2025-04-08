package com.lyca2206;

import com.lyca2206.controller.application.Application;
import com.lyca2206.controller.application.CommandLineApplication;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.libraries.command.processor.MapCommandProcessor;
import com.lyca2206.model.Exercise;
import com.lyca2206.model.Role;
import com.lyca2206.repository.abstraction.AuthenticationRepository;
import com.lyca2206.repository.abstraction.ExerciseRepository;
import com.lyca2206.repository.abstraction.LogRepository;
import com.lyca2206.repository.abstraction.WorkoutRepository;
import com.lyca2206.repository.implementation.InMemoryAuthentication;
import com.lyca2206.repository.implementation.InMemoryExercise;
import com.lyca2206.repository.implementation.InMemoryLog;
import com.lyca2206.repository.implementation.InMemoryWorkout;
import com.lyca2206.utilities.command.list.factory.CommandsProvidersFactory;
import com.lyca2206.utilities.command.list.factory.providers.AdminCommandsProvider;
import com.lyca2206.utilities.command.list.factory.providers.RegularCommandsProvider;
import com.lyca2206.utilities.command.list.factory.providers.SignedOutCommandsProvider;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

//TODO. Refactor this class.
//TODO. Improve navigation.

public class Main {
    public static void main(String[] args) {
        Reader reader = new InputStreamReader(System.in);
        CommandProcessor processor = new MapCommandProcessor(new HashMap<>());
        Application application = new CommandLineApplication(reader, processor);
        CommandsProvidersFactory providersFactory = new CommandsProvidersFactory(new HashSet<>());
        AuthenticationRepository authenticationRepository = new InMemoryAuthentication(new HashMap<>());

        ExerciseRepository exerciseRepository = new InMemoryExercise(new HashMap<>());
        exerciseRepository.createExercise(new Exercise("Running", "meters", (float) Math.random() * 256));
        exerciseRepository.createExercise(new Exercise("Cycling", "meters", (float) Math.random() * 256));
        exerciseRepository.createExercise(new Exercise("Swimming", "meters", (float) Math.random() * 256));
        exerciseRepository.createExercise(new Exercise("Jump", "reps", (float) Math.random() * 256));
        exerciseRepository.createExercise(new Exercise("Rope", "reps", (float) Math.random() * 256));
        exerciseRepository.createExercise(new Exercise("Push-Ups", "reps", (float) Math.random() * 256));
        exerciseRepository.createExercise(new Exercise("Squats", "reps", (float) Math.random() * 256));

        WorkoutRepository workoutRepository = new InMemoryWorkout(new HashMap<>());

        LogRepository logRepository = new InMemoryLog(new HashMap<>(), LinkedList::new);

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
                        reader,
                        exerciseRepository,
                        workoutRepository,
                        LinkedList::new
                ),

                new RegularCommandsProvider(
                        providersFactory,
                        processor,
                        Role.REGULAR.name(),
                        workoutRepository,
                        logRepository,
                        reader
                )
        ));

        processor.changeCommands(providersFactory.createCommands("SIGNED_OUT"));
        application.run();
    }
}
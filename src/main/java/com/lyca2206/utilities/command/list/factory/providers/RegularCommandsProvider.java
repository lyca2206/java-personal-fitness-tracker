package com.lyca2206.utilities.command.list.factory.providers;

import com.lyca2206.controller.commands.LogWorkoutCommand;
import com.lyca2206.controller.commands.SignOutCommand;
import com.lyca2206.controller.commands.ViewLogCommand;
import com.lyca2206.controller.commands.ViewWorkoutCommand;
import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.ExerciseLog;
import com.lyca2206.repository.abstraction.LogRepository;
import com.lyca2206.repository.abstraction.WorkoutRepository;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;
import com.lyca2206.utilities.command.list.factory.CommandsProvider;

import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class RegularCommandsProvider extends CommandsProvider {
    private final Reader reader;
    private final LogRepository logRepository;
    private final WorkoutRepository workoutRepository;
    private final Supplier<List<ExerciseLog>> listSupplier;

    public RegularCommandsProvider(
            CommandsFactory commandsFactory,
            CommandProcessor processor,
            String key,
            WorkoutRepository workoutRepository,
            LogRepository logRepository,
            Reader reader,
            Supplier<List<ExerciseLog>> listSupplier
    ) {
        super(commandsFactory, processor, key);
        this.reader = reader;
        this.logRepository = logRepository;
        this.workoutRepository = workoutRepository;
        this.listSupplier = listSupplier;
    }

    @Override
    public Collection<Command> createCommands() {
        return List.of(
                new ViewWorkoutCommand(
                        processor,
                        "ViewWorkout",
                        "ViewWorkout [Name : Text] : If a name is provided, it shows the information of a specific workout. Otherwise, it shows all available workouts",
                        workoutRepository
                ),

                new LogWorkoutCommand(
                        processor,
                        "LogWorkout",
                        "LogWorkout <WorkoutName : Text> : Logs the times for each exercise in a workout",
                        reader,
                        logRepository,
                        workoutRepository,
                        listSupplier
                ),

                new ViewLogCommand(
                        processor,
                        "ViewLog",
                        "ViewLog [Index : Integer] : If an integer is provided, it shows the information of a specific log. Otherwise, it shows all available logs",
                        logRepository
                ),

                new SignOutCommand(
                        processor,
                        "SignOut",
                        "SignOut : Signs out from the application",
                        commandsFactory
                )
        );
    }
}
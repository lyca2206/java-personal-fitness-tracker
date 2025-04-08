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
                        "viewWorkout",
                        "viewWorkout [name] - Shows the information of the given workout",
                        workoutRepository
                ),

                new LogWorkoutCommand(
                        processor,
                        "logWorkout",
                        "logWorkout [name] - Allows you to enter the taken minutes to complete each workoutExercise in a workout",
                        reader,
                        logRepository,
                        workoutRepository,
                        listSupplier
                ),

                new ViewLogCommand(
                        processor,
                        "viewWorkoutLog",
                        "viewWorkoutLog [index] - Shows up the log of a given workout, specifying the time taken in each workoutExercise along with the timestamp",
                        logRepository
                ),

                new SignOutCommand(
                        processor,
                        "signOut",
                        "signOut - Signs out from the application",
                        commandsFactory
                )
        );
    }
}
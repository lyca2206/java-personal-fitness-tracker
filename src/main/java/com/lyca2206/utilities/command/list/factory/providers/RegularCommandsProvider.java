package com.lyca2206.utilities.command.list.factory.providers;

import com.lyca2206.controller.commands.LogWorkoutCommand;
import com.lyca2206.controller.commands.SignOutCommand;
import com.lyca2206.controller.commands.ViewLogCommand;
import com.lyca2206.controller.commands.ViewWorkoutCommand;
import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.repository.abstraction.LogRepository;
import com.lyca2206.repository.abstraction.WorkoutRepository;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;
import com.lyca2206.utilities.command.list.factory.CommandsProvider;

import java.io.Reader;
import java.util.Collection;
import java.util.List;

public class RegularCommandsProvider extends CommandsProvider {
    private final WorkoutRepository workoutRepository;
    private final LogRepository logRepository;
    private final Reader reader;

    public RegularCommandsProvider(CommandsFactory commandsFactory, CommandProcessor processor, String key, WorkoutRepository workoutRepository, LogRepository logRepository, Reader reader) {
        super(commandsFactory, processor, key);
        this.workoutRepository = workoutRepository;
        this.logRepository = logRepository;
        this.reader = reader;
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
                        "logWorkout [name] - Allows you to enter the taken minutes to complete each exercise in a workout",
                        reader,
                        workoutRepository,
                        logRepository
                ),

                new ViewLogCommand(
                        processor,
                        "viewWorkoutLog",
                        "viewWorkoutLog [name] - Shows up the log of a given workout, specifying the time taken in each exercise along with the timestamp"
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
package com.lyca2206.utilities.command.list.factory.providers;

import com.lyca2206.controller.commands.CreateExerciseCommand;
import com.lyca2206.controller.commands.CreateWorkoutCommand;
import com.lyca2206.controller.commands.SignOutCommand;
import com.lyca2206.controller.commands.ViewWorkoutCommand;
import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.WorkoutExercise;
import com.lyca2206.repository.abstraction.ExerciseRepository;
import com.lyca2206.repository.abstraction.WorkoutRepository;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;
import com.lyca2206.utilities.command.list.factory.CommandsProvider;

import java.io.Reader;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class AdminCommandsProvider extends CommandsProvider {
    private final Reader reader;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;
    private final Supplier<List<WorkoutExercise>> listSupplier;

    public AdminCommandsProvider(
            CommandsFactory commandsFactory,
            CommandProcessor processor,
            String key,
            Reader reader,
            ExerciseRepository exerciseRepository,
            WorkoutRepository workoutRepository,
            Supplier<List<WorkoutExercise>> listSupplier
    ) {
        super(commandsFactory, processor, key);
        this.reader = reader;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
        this.listSupplier = listSupplier;
    }

    @Override
    public Collection<Command> createCommands() {
        return List.of(
                new CreateExerciseCommand(
                        processor,
                        "CreateExercise",
                        "CreateExercise <Name : Text> <MeasureUnit : Text> <CaloriesPerUnit : Decimal> : Creates a new exercise with the provided information",
                        exerciseRepository
                ),

                new CreateWorkoutCommand(
                        processor,
                        "CreateWorkout",
                        "CreateWorkout <Name : Text> : Prompts the user to create a new workout, providing the necessary information for it",
                        reader,
                        workoutRepository,
                        exerciseRepository,
                        listSupplier
                ),

                new ViewWorkoutCommand(
                        processor,
                        "ViewWorkout",
                        "ViewWorkout [Index : Integer] : If an integer is provided, it shows the information of a specific log. Otherwise, it shows all available logs",
                        workoutRepository
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
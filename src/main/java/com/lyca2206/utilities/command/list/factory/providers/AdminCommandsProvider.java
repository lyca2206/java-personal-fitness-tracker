package com.lyca2206.utilities.command.list.factory.providers;

import com.lyca2206.controller.commands.CreateExerciseCommand;
import com.lyca2206.controller.commands.CreateWorkoutCommand;
import com.lyca2206.controller.commands.SignOutCommand;
import com.lyca2206.controller.commands.ViewWorkoutCommand;
import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.repository.abstraction.ExerciseRepository;
import com.lyca2206.repository.abstraction.WorkoutRepository;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;
import com.lyca2206.utilities.command.list.factory.CommandsProvider;

import java.io.Reader;
import java.util.Collection;
import java.util.List;

public class AdminCommandsProvider extends CommandsProvider {
    private final Reader reader;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public AdminCommandsProvider(CommandsFactory commandsFactory, CommandProcessor processor, String key, Reader reader, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        super(commandsFactory, processor, key);
        this.reader = reader;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    @Override
    public Collection<Command> createCommands() {
        return List.of(
                new CreateExerciseCommand(
                        processor,
                        "createExercise",
                        "createExercise [name] [measureUnit] [caloriesPerUnit] - Creates a new exercise inside the application",
                        exerciseRepository
                ),

                new CreateWorkoutCommand(
                        processor,
                        "createWorkout",
                        "createWorkout [name] [description] [notes] - Creates a new workout inside the application, prompting to select various exercises by typing [exerciseName] [# sets] [# units]",
                        reader,
                        exerciseRepository,
                        workoutRepository
                ),

                new ViewWorkoutCommand(
                        processor,
                        "viewWorkout",
                        "viewWorkout [name] - Shows the information of the given workout",
                        workoutRepository
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
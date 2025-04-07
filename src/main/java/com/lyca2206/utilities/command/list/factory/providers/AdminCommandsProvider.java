package com.lyca2206.utilities.command.list.factory.providers;

import com.lyca2206.controller.commands.CreateExerciseCommand;
import com.lyca2206.controller.commands.CreateWorkoutCommand;
import com.lyca2206.controller.commands.SignOutCommand;
import com.lyca2206.controller.commands.ViewWorkoutCommand;
import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.utilities.command.list.factory.CommandsFactory;
import com.lyca2206.utilities.command.list.factory.CommandsProvider;

import java.util.Collection;
import java.util.List;

public class AdminCommandsProvider extends CommandsProvider {
    public AdminCommandsProvider(CommandsFactory commandsFactory, CommandProcessor processor, String key) {
        super(commandsFactory, processor, key);
    }

    @Override
    public Collection<Command> createCommands() {
        return List.of(
                new SignOutCommand(
                        processor,
                        "signOut",
                        "signOut - Signs out from the application",
                        commandsFactory
                ),

                new CreateExerciseCommand(
                        processor,
                        "createExercise",
                        "createExercise [name] [measureUnit] [caloriesPerUnit] - Creates a new exercise inside the application"
                ),

                new CreateWorkoutCommand(
                        processor,
                        "createWorkout",
                        "createWorkout [name] [description] [notes] - Creates a new workout inside the application, prompting to select various exercises"
                ),

                new ViewWorkoutCommand(
                        processor,
                        "viewWorkout",
                        "viewWorkout [name] - Shows the information of the given workout"
                )
        );
    }
}
package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.Exercise;
import com.lyca2206.repository.abstraction.ExerciseRepository;

public class CreateExerciseCommand extends Command {
    private final ExerciseRepository exerciseRepository;

    public CreateExerciseCommand(CommandProcessor processor, String key, String information, ExerciseRepository exerciseRepository) {
        super(processor, key, information);
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public void execute(String[] tokens) {
        try {

            Exercise exercise = new Exercise(
                    tokens[0],
                    tokens[1],
                    Float.parseFloat(tokens[2])
            );

            exerciseRepository.createExercise(exercise);
            System.out.println("Exercise created successfully");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not enough information given to create an exercise");
        } catch (NumberFormatException e) {
            System.out.println("Given value type is not compatible");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
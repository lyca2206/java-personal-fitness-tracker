package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.Exercise;
import com.lyca2206.repository.abstraction.ExerciseRepository;

public class CreateExerciseCommand extends Command {
    private final ExerciseRepository exerciseRepository;

    public CreateExerciseCommand(
            CommandProcessor processor,
            String key,
            String information,
            ExerciseRepository exerciseRepository
    ) {
        super(processor, key, information);
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public void execute(String[] tokens) {
        try {

            Exercise exercise = createExerciseInstance(tokens);
            exerciseRepository.createExercise(exercise);
            System.out.println("\nThe workoutExercise has been created successfully\n");

        } catch (IndexOutOfBoundsException e) {

            System.out.println("\nThe provided information isn't enough to create a new workoutExercise\n");

        } catch (NumberFormatException e) {

            System.out.println("\nThe provided type of one of the parameters is not valid\n");

        } catch (Exception e) {

            System.out.println("\n" + e.getMessage() + "\n");

        }
    }

    private Exercise createExerciseInstance(String[] tokens) {
        String name = tokens[0];
        String measureUnit = tokens[1];
        float caloriesPerUnit = Float.parseFloat(tokens[2]);

        return new Exercise(name, measureUnit, caloriesPerUnit);
    }
}
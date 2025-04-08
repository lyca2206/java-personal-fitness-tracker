package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.Workout;
import com.lyca2206.repository.abstraction.WorkoutRepository;

import javax.management.InstanceNotFoundException;

public class ViewWorkoutCommand extends Command {
    private final WorkoutRepository workoutRepository;
    public ViewWorkoutCommand(CommandProcessor processor, String key, String information, WorkoutRepository workoutRepository) {
        super(processor, key, information);
        this.workoutRepository = workoutRepository;
    }

    @Override
    public void execute(String[] tokens) {
        try {
            Workout workout = workoutRepository.getWorkout(tokens[0]);
            System.out.println(workout.getName());
            System.out.println(workout.getDescription());

            workout.getExercises().forEach(workoutExercise ->
                    System.out.println(
                            workoutExercise.getExercise().getName() + ": " +
                            workoutExercise.getSets() + " sets of " +
                            workoutExercise.getUnits() + " " + workoutExercise.getExercise().getMeasureUnit()
                    )
            );

            System.out.println(workout.getNotes());
        } catch (ArrayIndexOutOfBoundsException e) {
            workoutRepository.getWorkouts().forEach(workout ->
                    System.out.println(workout.getName() + " - " + workout.getDescription())
            );
        } catch (InstanceNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
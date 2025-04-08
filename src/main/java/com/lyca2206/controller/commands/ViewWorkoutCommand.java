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
            System.out.println(workout.name());
            System.out.println(workout.description());

            workout.workoutExercises().forEach(workoutExercise ->
                    System.out.println(
                            workoutExercise.exercise().name() + ": " +
                            workoutExercise.sets() + " sets of " +
                            workoutExercise.units() + " " + workoutExercise.exercise().measureUnit()
                    )
            );

            System.out.println(workout.notes());
        } catch (IndexOutOfBoundsException e) {
            workoutRepository.getWorkouts().forEach(workout ->
                    System.out.println(workout.name() + " - " + workout.description())
            );
        } catch (InstanceNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
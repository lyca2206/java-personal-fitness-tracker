package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.Workout;
import com.lyca2206.repository.abstraction.WorkoutRepository;

import javax.management.InstanceNotFoundException;
import java.util.List;

public class ViewWorkoutCommand extends Command {
    private final WorkoutRepository workoutRepository;

    public ViewWorkoutCommand(
            CommandProcessor processor,
            String key,
            String information,
            WorkoutRepository workoutRepository
    ) {
        super(processor, key, information);
        this.workoutRepository = workoutRepository;
    }

    @Override
    public void execute(String[] tokens) {
        try {

            getWorkout(tokens);

        } catch (IndexOutOfBoundsException e) {

            getWorkouts();

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }

    private void getWorkout(String[] tokens) throws InstanceNotFoundException {
        String name = tokens[0];

        Workout workout = workoutRepository.getWorkout(name);

        System.out.println("\n" + workout.getAllInformation() + "\n");
    }

    private void getWorkouts() {
        List<Workout> workouts = workoutRepository.getWorkouts();

        System.out.println();

        workouts.forEach(workout ->
                System.out.println(workout.getSummary())
        );

        System.out.println();
    }
}
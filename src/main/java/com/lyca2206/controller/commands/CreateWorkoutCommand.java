package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.Workout;
import com.lyca2206.model.WorkoutExercise;
import com.lyca2206.repository.abstraction.ExerciseRepository;
import com.lyca2206.repository.abstraction.WorkoutRepository;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateWorkoutCommand extends Command {
    private final Reader reader;
    private final ExerciseRepository exerciseRepository;
    private final WorkoutRepository workoutRepository;

    public CreateWorkoutCommand(CommandProcessor processor, String key, String information, Reader reader, ExerciseRepository exerciseRepository, WorkoutRepository workoutRepository) {
        super(processor, key, information);
        this.reader = reader;
        this.exerciseRepository = exerciseRepository;
        this.workoutRepository = workoutRepository;
    }

    //TODO. This needs a refactor, if possible.
    @Override
    public void execute(String[] tokens) {
        try {

            String name = tokens[0];
            String description = tokens[1];
            String notes = tokens[2];

            BufferedReader bufferedReader = new BufferedReader(reader);
            List<WorkoutExercise> exercises = new ArrayList<>();

            boolean isCreatingWorkout = true;
            while (isCreatingWorkout) {
                String line = bufferedReader.readLine();
                String[] workoutAttributes = line.trim().split(" ");

                if (Objects.equals(workoutAttributes[0], "")) {
                    isCreatingWorkout = false;
                } else {
                    try {
                        WorkoutExercise exercise = new WorkoutExercise(
                                exerciseRepository.getExercise(workoutAttributes[0]),
                                Integer.parseInt(workoutAttributes[1]),
                                Integer.parseInt(workoutAttributes[2])
                        );

                        exercises.add(exercise);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Not enough information to add the exercise to the workout");
                    } catch (NumberFormatException e) {
                        System.out.println("The given types are not compatible");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            if (!exercises.isEmpty()) {
                Workout workout = new Workout(name, description, exercises, notes);
                workoutRepository.createWorkout(workout);

                System.out.println("Workout created successfully");
            } else {
                System.out.println("Not enough exercises given to create the workout");
            }

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not enough parameters to create a workout");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
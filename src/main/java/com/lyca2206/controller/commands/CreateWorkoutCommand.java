package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.Workout;
import com.lyca2206.model.WorkoutExercise;
import com.lyca2206.repository.abstraction.ExerciseRepository;
import com.lyca2206.repository.abstraction.WorkoutRepository;

import java.io.BufferedReader;
import java.io.IOException;
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

    @Override
    public void execute(String[] tokens) {
        try {

            Workout workout = new Workout(tokens[0], tokens[1], new ArrayList<>(), tokens[2]);
            addToListWhileItReceivesInput(workout.getExercises());
            saveWorkoutIfExercisesAreNotEmpty(workout);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not enough parameters to create a workout");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void addToListWhileItReceivesInput(List<WorkoutExercise> list) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(reader);
        boolean isReceivingInput = true;

        while (isReceivingInput) {
            String line = bufferedReader.readLine();
            String[] workoutAttributes = line.trim().split(" ");

            if (Objects.equals(workoutAttributes[0], "")) { isReceivingInput = false; }
            else { addWorkoutExerciseToList(workoutAttributes, list); }
        }
    }

    private void addWorkoutExerciseToList(String[] workoutAttributes, List<WorkoutExercise> list) {
        try {

            WorkoutExercise workoutExercise = new WorkoutExercise(
                    exerciseRepository.getExercise(workoutAttributes[0]),
                    Integer.parseInt(workoutAttributes[1]),
                    Integer.parseInt(workoutAttributes[2])
            );

            list.add(workoutExercise);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not enough information to add the exercise to the workout");
        } catch (NumberFormatException e) {
            System.out.println("The given types are not compatible");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void saveWorkoutIfExercisesAreNotEmpty(Workout workout) {
        if (!workout.getExercises().isEmpty()) {
            workoutRepository.createWorkout(workout);
            System.out.println("Workout created successfully");
        } else {
            System.out.println("Not enough exercises given to create the workout");
        }
    }
}
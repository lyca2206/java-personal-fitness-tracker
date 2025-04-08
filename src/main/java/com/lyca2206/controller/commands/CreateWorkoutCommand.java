package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.model.Exercise;
import com.lyca2206.model.Workout;
import com.lyca2206.model.WorkoutExercise;
import com.lyca2206.repository.abstraction.ExerciseRepository;
import com.lyca2206.repository.abstraction.WorkoutRepository;

import javax.management.InstanceNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.function.Supplier;

public class CreateWorkoutCommand extends Command {
    private final Reader reader;
    private final WorkoutRepository workoutRepository;
    private final ExerciseRepository exerciseRepository;
    private final Supplier<List<WorkoutExercise>> listSupplier;

    public CreateWorkoutCommand(
            CommandProcessor processor,
            String key, String information,
            Reader reader,
            WorkoutRepository workoutRepository,
            ExerciseRepository exerciseRepository, Supplier<List<WorkoutExercise>> listSupplier
    ) {
        super(processor, key, information);
        this.reader = reader;
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.listSupplier = listSupplier;
    }

    @Override
    public void execute(String[] tokens) {
        try {

            BufferedReader bufferedReader = new BufferedReader(reader);

            Workout workout = createWorkoutInstance(bufferedReader, tokens);
            readInputToAddWorkoutExercises(bufferedReader, workout);
            saveWorkout(workout);

        } catch (IndexOutOfBoundsException e) {

            System.out.println("\nThe provided information isn't enough to create a new workout\n");

        } catch (Exception e) {

            System.out.println("\n" + e.getMessage() + "\n");

        }
    }

    private Workout createWorkoutInstance(BufferedReader bufferedReader, String[] tokens) throws IOException {
        String name = tokens[0];
        List<WorkoutExercise> workoutExercises = listSupplier.get();

        System.out.println("\nWrite the description of the new workout\n");
        String description = bufferedReader.readLine();

        System.out.println("\nWrite the notes of the new workout\n");
        String notes = bufferedReader.readLine();

        return new Workout(name, description, workoutExercises, notes);
    }

    private void readInputToAddWorkoutExercises(BufferedReader bufferedReader, Workout workout) throws IOException {
        boolean isRunning = true;

        System.out.println(
                "\nSelect the exercises you want to add to the workout by typing [Exercise Name] [# Sets] [# Units].\nPress Enter when you are done:\n"
        );

        while (isRunning) {
            String line = bufferedReader.readLine();
            String[] tokens = line.trim().split(" ");

            String name = tokens[0];

            if (name.isEmpty()) {
                isRunning = false;
            } else {
                addWorkoutExerciseToWorkout(tokens, workout);
            }
        }
    }

    private void addWorkoutExerciseToWorkout(String[] tokens, Workout workout) {
        try {

            WorkoutExercise workoutExercise = createWorkoutExerciseInstance(tokens);
            workout.workoutExercises().add(workoutExercise);

        } catch (IndexOutOfBoundsException e) {

            System.out.println("\nThe provided information isn't enough to create a new workout workoutExercise\n");

        } catch (NumberFormatException e) {

            System.out.println("\nThe provided types aren't compatible\n");

        } catch (Exception e) {

            System.out.println("\n" + e.getMessage() + "\n");

        }
    }

    private WorkoutExercise createWorkoutExerciseInstance(String[] tokens) throws InstanceNotFoundException {
        String exerciseName = tokens[0];
        int sets = Integer.parseInt(tokens[1]);
        float units = Float.parseFloat(tokens[2]);

        Exercise exercise = exerciseRepository.getExercise(exerciseName);

        return new WorkoutExercise(exercise, sets, units);
    }

    private void saveWorkout(Workout workout) {
        if (workout.workoutExercises().isEmpty()) {
            System.out.println("\nThere are not enough workout exercises to create a new workout\n");
            return;
        }

        workoutRepository.createWorkout(workout);
        System.out.println("\nThe workout has been created successfully\n");
    }
}
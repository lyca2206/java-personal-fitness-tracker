package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.libraries.session.Session;
import com.lyca2206.model.*;
import com.lyca2206.repository.abstraction.LogRepository;
import com.lyca2206.repository.abstraction.WorkoutRepository;

import javax.management.InstanceNotFoundException;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.List;
import java.util.function.Supplier;

public class LogWorkoutCommand extends Command {
    private final Reader reader;
    private final WorkoutRepository workoutRepository;
    private final LogRepository logRepository;
    private final Supplier<List<ExerciseLog>> listSupplier;

    public LogWorkoutCommand(
            CommandProcessor processor,
            String key,
            String information,
            Reader reader,
            LogRepository logRepository,
            WorkoutRepository workoutRepository, Supplier<List<ExerciseLog>> listSupplier
    ) {
        super(processor, key, information);
        this.reader = reader;
        this.logRepository = logRepository;
        this.workoutRepository = workoutRepository;
        this.listSupplier = listSupplier;
    }

    @Override
    public void execute(String[] tokens) {
        try {
            Workout workout = createWorkoutInstance(tokens);
            List<ExerciseLog> exerciseLogs = createExerciseLogs(workout);
            User user = (User) Session.getInstance().getPrincipal();

            Log log = new Log(workout, exerciseLogs, user);

            System.out.println("\nThe log has been created successfully\n");

            logRepository.logWorkout(log);
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }

    private Workout createWorkoutInstance(String[] tokens) throws InstanceNotFoundException {
        String name = tokens[0];
        return workoutRepository.getWorkout(name);
    }

    private List<ExerciseLog> createExerciseLogs(Workout workout) {
        List<ExerciseLog> logs = listSupplier.get();

        System.out.println("\nEnter the minutes it took to complete each exercise:\n");

        workout.workoutExercises().forEach(workoutExercise -> {
            System.out.print(workoutExercise.exercise().name() + ": ");
            float minutes = readValue();
            logs.add(new ExerciseLog(workoutExercise, minutes));
        });

        return logs;
    }

    private float readValue() {
        BufferedReader bufferedReader = new BufferedReader(reader);
        try {

            String line = bufferedReader.readLine();
            String[] tokens = line.trim().split(" ");

            return Float.parseFloat(tokens[0]);

        } catch (NumberFormatException e) {

            System.out.println("\nThe provided types aren't compatible. Re-enter the value:\n");
            return readValue();

        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());

        }
    }
}
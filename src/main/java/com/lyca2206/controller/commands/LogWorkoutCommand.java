package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.libraries.session.Session;
import com.lyca2206.model.ExerciseLog;
import com.lyca2206.model.Log;
import com.lyca2206.model.User;
import com.lyca2206.model.Workout;
import com.lyca2206.repository.abstraction.LogRepository;
import com.lyca2206.repository.abstraction.WorkoutRepository;

import javax.management.InstanceNotFoundException;
import java.io.BufferedReader;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

//TODO. Pending refactor.
public class LogWorkoutCommand extends Command {
    private final Reader reader;
    private final WorkoutRepository workoutRepository;
    private final LogRepository logRepository;

    public LogWorkoutCommand(CommandProcessor processor, String key, String information, Reader reader, WorkoutRepository workoutRepository, LogRepository logRepository) {
        super(processor, key, information);
        this.reader = reader;
        this.workoutRepository = workoutRepository;
        this.logRepository = logRepository;
    }

    @Override
    public void execute(String[] tokens) {
        try {

            Workout workout = workoutRepository.getWorkout(tokens[0]);

            List<ExerciseLog> exerciseLogs = new LinkedList<>();
            workout.workoutExercises().forEach(workoutExercise -> {
                System.out.println(workoutExercise.exercise().name() + ": ");
                float minutes = readMinuteValue(new BufferedReader(reader));
                exerciseLogs.add(new ExerciseLog(workoutExercise, minutes));
            });

            Log log = new Log(
                    workout,
                    exerciseLogs,
                    (User) Session.getInstance().getPrincipal()
            );

            logRepository.logWorkout(log);
            System.out.println("Workout was logged successfully");

        } catch (InstanceNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Not enough information provided to log a workout");
        }
    }

    private float readMinuteValue(BufferedReader bufferedReader) {
        try {
            String line = bufferedReader.readLine();
            String[] exerciseLog = line.trim().split(" ");

            return Float.parseFloat(exerciseLog[0]);
        } catch (NumberFormatException e) {
            System.out.println("The given type isn't valid, try again");
            return readMinuteValue(bufferedReader);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
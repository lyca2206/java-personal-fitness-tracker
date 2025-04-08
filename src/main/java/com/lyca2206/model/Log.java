package com.lyca2206.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Log {
    private final Workout workout;
    private final List<ExerciseLog> exerciseLogs;
    private final User user;
    private final LocalDateTime time;

    public Log(Workout workout, List<ExerciseLog> exerciseLogs, User user) {
        this.workout = workout;
        this.exerciseLogs = exerciseLogs;
        this.user = user;
        this.time = LocalDateTime.now();
    }

    public Workout getWorkout() {
        return workout;
    }

    public List<ExerciseLog> getExerciseLogs() {
        return exerciseLogs;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getSummary() {
        return "> Workout: " + workout.name() + "\n" + "  Date: " + time.format(DateTimeFormatter.ISO_DATE);
    }

    public String getAllInformation() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("Workout Details for ")
                .append(workout.name())
                .append(" on ")
                .append(time.format(DateTimeFormatter.ISO_DATE))
                .append(":")
                .append("\n\n");

        exerciseLogs.forEach(exerciseLog -> {
            builder
                    .append(exerciseLog.workoutExercise().exercise().name())
                    .append(": ")
                    .append(exerciseLog.minutes())
                    .append(" minutes")
                    .append("\n");
        });

        builder
                .append("\nTotal time: TODO")
                .append("\nCalories: TODO");

        return builder.toString();
    }

    @Override
    public String toString() {
        return "Log{" +
                "workout=" + workout +
                ", exerciseLogs=" + exerciseLogs +
                ", user=" + user +
                ", time=" + time +
                '}';
    }
}
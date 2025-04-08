package com.lyca2206.model;

import java.time.LocalDateTime;
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
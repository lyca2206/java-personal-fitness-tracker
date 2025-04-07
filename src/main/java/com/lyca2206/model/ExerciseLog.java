package com.lyca2206.model;

public class ExerciseLog {
    private final WorkoutExercise exercise;
    private final float minutes;

    public ExerciseLog(WorkoutExercise exercise, float minutes) {
        this.exercise = exercise;
        this.minutes = minutes;
    }

    private void validateMinutes() {
        if (minutes <= 0) {
            throw new IllegalArgumentException("The amount of minutes needs to be a positive number");
        }
    }

    public WorkoutExercise getExercise() {
        return exercise;
    }

    public float getMinutes() {
        return minutes;
    }
}

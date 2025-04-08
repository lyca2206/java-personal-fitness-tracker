package com.lyca2206.model;

public record ExerciseLog(WorkoutExercise exercise, float minutes) {
    public ExerciseLog {
        validateMinutes();
    }

    private void validateMinutes() {
        if (minutes <= 0) {
            throw new IllegalArgumentException("The amount of minutes needs to be a positive number");
        }
    }

    @Override
    public String toString() {
        return "ExerciseLog{" +
                "exercise=" + exercise +
                ", minutes=" + minutes +
                '}';
    }
}
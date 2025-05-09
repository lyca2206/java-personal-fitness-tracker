package com.lyca2206.model;

public record ExerciseLog(WorkoutExercise workoutExercise, float minutes) {
    public ExerciseLog {
        validateMinutes(minutes);
    }

    private void validateMinutes(float minutes) {
        if (minutes <= 0) {
            throw new IllegalArgumentException("The minute amount needs to be a positive number");
        }
    }

    @Override
    public String toString() {
        return "ExerciseLog{" +
                "workoutExercise=" + workoutExercise +
                ", minutes=" + minutes +
                '}';
    }
}
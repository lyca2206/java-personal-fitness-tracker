package com.lyca2206.model;

public record WorkoutExercise(Exercise exercise, int sets, float units) {
    public WorkoutExercise {
        validateExercise(exercise);
        validateSets(sets);
        validateUnits(units);
    }

    private void validateExercise(Exercise exercise) {
        if (exercise == null) {
            throw new IllegalArgumentException("The given workoutExercise must be a non null value");
        }
    }

    private void validateSets(int sets) {
        if (sets <= 0) {
            throw new IllegalArgumentException("The set amount must be higher than 0");
        }
    }

    private void validateUnits(float units) {
        if (units <= 0) {
            throw new IllegalArgumentException("The units must be higher than 0");
        }
    }

    @Override
    public String toString() {
        return "WorkoutExercise{" +
                "workoutExercise=" + exercise +
                ", sets=" + sets +
                ", units=" + units +
                '}';
    }
}
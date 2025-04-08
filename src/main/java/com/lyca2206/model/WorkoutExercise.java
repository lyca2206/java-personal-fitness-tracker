package com.lyca2206.model;

public record WorkoutExercise(Exercise exercise, int sets, float units) {
    public WorkoutExercise {
        validateExercise(exercise);
        validateSets(sets);
        validateUnits(units);
    }

    private void validateExercise(Exercise exercise) {
        if (exercise == null) {
            throw new IllegalArgumentException("The given exercise doesn't exist (it is null)");
        }
    }

    private void validateSets(int sets) {
        if (sets <= 0) {
            throw new IllegalArgumentException("The number of sets needs to be a positive number");
        }
    }

    private void validateUnits(float units) {
        if (units <= 0) {
            throw new IllegalArgumentException("The number of units needs to be a positive number");
        }
    }

    @Override
    public String toString() {
        return "WorkoutExercise{" +
                "exercise=" + exercise +
                ", sets=" + sets +
                ", units=" + units +
                '}';
    }
}
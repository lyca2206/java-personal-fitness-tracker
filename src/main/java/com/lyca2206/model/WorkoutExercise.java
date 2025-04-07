package com.lyca2206.model;

public class WorkoutExercise {
    private final Exercise exercise;
    private final int sets;
    private final float units;

    public WorkoutExercise(Exercise exercise, int sets, float units) {
        validateExercise(exercise);
        validateSets(sets);
        validateUnits(units);

        this.exercise = exercise;
        this.sets = sets;
        this.units = units;
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

    public Exercise getExercise() {
        return exercise;
    }

    public int getSets() {
        return sets;
    }

    public float getUnits() {
        return units;
    }
}

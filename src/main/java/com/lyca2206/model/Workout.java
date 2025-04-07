package com.lyca2206.model;

import java.util.List;

public class Workout {
    private final String name;
    private final String description;
    private final List<WorkoutExercise> exercises;
    private final String notes;

    public Workout(String name, String description, List<WorkoutExercise> exercises, String notes) {
        validateName(name);
        validateDescription(description);
        validateNotes(notes);

        this.name = name;
        this.description = description;
        this.exercises = exercises;
        this.notes = notes;
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The workout name needs to be non empty");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("The description needs to be non empty");
        }
    }

    private void validateNotes(String notes) {
        if (notes == null) {
            throw new IllegalArgumentException("The description needs to be non null");
        }
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<WorkoutExercise> getExercises() {
        return exercises;
    }

    public String getNotes() {
        return notes;
    }
}

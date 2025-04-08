package com.lyca2206.model;

import java.util.List;

public record Workout(String name, String description, List<WorkoutExercise> exercises, String notes) {
    public Workout {
        validateName(name);
        validateDescription(description);
        validateNotes(notes);

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

    @Override
    public String toString() {
        return "Workout{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", exercises=" + exercises +
                ", notes='" + notes + '\'' +
                '}';
    }
}
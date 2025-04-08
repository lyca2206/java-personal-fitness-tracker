package com.lyca2206.model;

import java.util.List;

public record Workout(String name, String description, List<WorkoutExercise> workoutExercises, String notes) {
    public Workout {
        validateName(name);
        validateDescription(description);
        validateNotes(notes);
    }

    private void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("The given name must be a non null value or a non empty value");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("The given description must be a non null value or a non empty value");
        }
    }

    private void validateNotes(String notes) {
        if (notes == null) {
            throw new IllegalArgumentException("The given notes must be a non null value");
        }
    }

    public String getSummary() {
        return name + " - " + description;
    }

    public String getAllInformation() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("Workout Name: ").append(name).append("\n")
                .append("Description: ").append(description).append("\n")
                .append("\n")
                .append("Exercises: " + "\n");

        workoutExercises.forEach(workoutExercise -> builder
                .append(workoutExercise.exercise().name())
                .append(": ")
                .append(workoutExercise.sets())
                .append(" sets of ")
                .append(workoutExercise.units())
                .append(" ")
                .append(workoutExercise.exercise().measureUnit())
                .append("\n")
        );

        builder
                .append("\n")
                .append("Notes: ").append(notes);

        return builder.toString();
    }

    @Override
    public String toString() {
        return "Workout{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", workoutExercises=" + workoutExercises +
                ", notes='" + notes + '\'' +
                '}';
    }
}
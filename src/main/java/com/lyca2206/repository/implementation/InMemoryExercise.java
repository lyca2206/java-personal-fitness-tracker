package com.lyca2206.repository.implementation;

import com.lyca2206.model.Exercise;
import com.lyca2206.repository.abstraction.ExerciseRepository;

import java.util.List;
import java.util.Map;

public class InMemoryExercise implements ExerciseRepository {
    private final Map<String, Exercise> exercises;

    public InMemoryExercise(Map<String, Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public void createExercise(Exercise exercise) {
        exercises.put(exercise.getName(), exercise);
    }

    @Override
    public List<Exercise> getExercises() {
        return exercises.values().stream().toList();
    }

    @Override
    public Exercise getExercise(String name) {
        return exercises.get(name);
    }
}

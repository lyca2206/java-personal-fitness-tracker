package com.lyca2206.repository.implementation;

import com.lyca2206.model.Exercise;
import com.lyca2206.repository.abstraction.ExerciseRepository;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Map;

public class InMemoryExercise implements ExerciseRepository {
    private final Map<String, Exercise> exercises;

    public InMemoryExercise(Map<String, Exercise> exercises) {
        this.exercises = exercises;
    }

    @Override
    public void createExercise(Exercise exercise) {
        exercises.put(exercise.name(), exercise);
    }

    @Override
    public List<Exercise> getExercises() {
        return exercises.values().stream().toList();
    }

    @Override
    public Exercise getExercise(String name) throws InstanceNotFoundException {
        Exercise exercise = exercises.get(name);

        if (exercise == null) {
            throw new InstanceNotFoundException("The given exercise couldn't be found in the system");
        }

        return exercise;
    }
}
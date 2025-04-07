package com.lyca2206.repository.abstraction;

import com.lyca2206.model.Exercise;

import java.util.List;

public interface ExerciseRepository {
    void createExercise(Exercise exercise);
    List<Exercise> getExercises();
    Exercise getExercise(String name);
}

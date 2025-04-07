package com.lyca2206.repository.implementation;

import com.lyca2206.model.Workout;
import com.lyca2206.repository.abstraction.WorkoutRepository;

import java.util.List;

public class InMemoryWorkout implements WorkoutRepository {
    @Override
    public Workout createWorkout(Workout workout) {
        return null;
    }

    @Override
    public List<Workout> getWorkouts() {
        return List.of();
    }

    @Override
    public Workout getWorkout(String name) {
        return null;
    }
}

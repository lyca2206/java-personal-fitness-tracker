package com.lyca2206.repository.implementation;

import com.lyca2206.model.Workout;
import com.lyca2206.repository.abstraction.WorkoutRepository;

import java.util.List;
import java.util.Map;

public class InMemoryWorkout implements WorkoutRepository {
    private final Map<String, Workout> workouts;

    public InMemoryWorkout(Map<String, Workout> workouts) {
        this.workouts = workouts;
    }

    @Override
    public void createWorkout(Workout workout) {
        workouts.put(workout.getName(), workout);
    }

    @Override
    public List<Workout> getWorkouts() {
        return workouts.values().stream().toList();
    }

    @Override
    public Workout getWorkout(String name) {
        return workouts.get(name);
    }
}

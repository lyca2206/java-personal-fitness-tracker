package com.lyca2206.repository.abstraction;

import com.lyca2206.model.Workout;

import java.util.List;

public interface WorkoutRepository {
    Workout createWorkout(Workout workout);
    List<Workout> getWorkouts();
    Workout getWorkout(String name);
}

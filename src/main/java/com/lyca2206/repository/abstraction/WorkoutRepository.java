package com.lyca2206.repository.abstraction;

import com.lyca2206.model.Workout;

public interface WorkoutRepository {
    Workout createWorkout(Workout workout);
    Workout getWorkout(String name);
}

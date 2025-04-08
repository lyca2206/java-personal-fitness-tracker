package com.lyca2206.repository.abstraction;

import com.lyca2206.model.Workout;

import javax.management.InstanceNotFoundException;
import java.util.List;

public interface WorkoutRepository {
    void createWorkout(Workout workout);

    List<Workout> getWorkouts();

    Workout getWorkout(String name) throws InstanceNotFoundException;
}
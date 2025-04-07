package com.lyca2206.repository.abstraction;

import com.lyca2206.model.Log;
import com.lyca2206.model.User;

public interface LogRepository {
    Log logWorkout(Log log);
    Log getLog(String name, User user);
}

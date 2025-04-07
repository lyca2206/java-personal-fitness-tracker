package com.lyca2206.repository.abstraction;

import com.lyca2206.model.Log;
import com.lyca2206.model.User;

import java.util.List;

public interface LogRepository {
    Log logWorkout(Log log);
    List<Log> getLogs(User user);
    Log getLog(String name, User user);
}

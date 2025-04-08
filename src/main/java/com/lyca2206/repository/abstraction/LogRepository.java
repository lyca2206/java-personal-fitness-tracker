package com.lyca2206.repository.abstraction;

import com.lyca2206.model.Log;
import com.lyca2206.model.User;

import javax.management.InstanceNotFoundException;
import java.util.List;

public interface LogRepository {
    void logWorkout(Log log);

    List<Log> getLogs(User user);

    Log getLog(int i, User user) throws InstanceNotFoundException;
}
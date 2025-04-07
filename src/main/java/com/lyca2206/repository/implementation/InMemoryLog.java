package com.lyca2206.repository.implementation;

import com.lyca2206.model.Log;
import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.LogRepository;

import java.util.List;

public class InMemoryLog implements LogRepository {
    @Override
    public Log logWorkout(Log log) {
        return null;
    }

    @Override
    public List<Log> getLogs(User user) {
        return List.of();
    }

    @Override
    public Log getLog(String name, User user) {
        return null;
    }
}

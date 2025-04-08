package com.lyca2206.repository.implementation;

import com.lyca2206.model.Log;
import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.LogRepository;

import javax.management.InstanceNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InMemoryLog implements LogRepository {
    private final Map<User, List<Log>> logs;

    public InMemoryLog(Map<User, List<Log>> logs) {
        this.logs = logs;
    }

    @Override
    public void logWorkout(Log log) {
        if (!logs.containsKey(log.getUser())) {
            logs.put(log.getUser(), new LinkedList<>());
        }

        List<Log> userLogs = logs.get(log.getUser());
        userLogs.add(log);
    }

    @Override
    public List<Log> getLogs(User user) {
        return logs.get(user);
    }

    @Override
    public Log getLog(int index, User user) throws InstanceNotFoundException {
        try {
            return logs.get(user).get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InstanceNotFoundException("The log with index " + index + " couldn't be found");
        }
    }
}

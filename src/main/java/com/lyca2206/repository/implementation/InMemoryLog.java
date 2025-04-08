package com.lyca2206.repository.implementation;

import com.lyca2206.model.Log;
import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.LogRepository;

import javax.management.InstanceNotFoundException;
import java.util.*;
import java.util.function.Supplier;

public class InMemoryLog implements LogRepository {
    private final Map<User, List<Log>> logs;
    private final Supplier<List<Log>> listSupplier;

    public InMemoryLog(Map<User, List<Log>> logs, Supplier<List<Log>> listSupplier) {
        this.logs = logs;
        this.listSupplier = listSupplier;
    }

    @Override
    public void logWorkout(Log log) {
        if (!logs.containsKey(log.getUser())) {
            logs.put(log.getUser(), listSupplier.get());
        }

        List<Log> logList = logs.get(log.getUser());
        logList.add(log);
    }

    @Override
    public List<Log> getLogs(User user) {
        return logs.get(user);
    }

    @Override
    public Log getLog(int i, User user) throws InstanceNotFoundException {
        try {

            return logs.get(user).get(i);

        } catch (Exception e) {

            throw new InstanceNotFoundException("The given log couldn't be found in the system");

        }
    }
}
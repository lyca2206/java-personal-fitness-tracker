package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.libraries.session.Session;
import com.lyca2206.model.Log;
import com.lyca2206.model.User;
import com.lyca2206.repository.abstraction.LogRepository;

import javax.management.InstanceNotFoundException;
import java.util.List;

public class ViewLogCommand extends Command {
    private final LogRepository logRepository;

    public ViewLogCommand(CommandProcessor processor, String key, String information, LogRepository logRepository) {
        super(processor, key, information);
        this.logRepository = logRepository;
    }

    @Override
    public void execute(String[] tokens) {
        try {
            Log log = logRepository.getLog(
                    Integer.parseInt(tokens[0]),
                    (User) Session.getInstance().getPrincipal()
            );

            System.out.println(log.getWorkout().name() + " - " + log.getTime());

            log.getExerciseLogs().forEach(exerciseLog ->
                    System.out.println(exerciseLog.exercise().exercise().name() + ": " + exerciseLog.minutes() + " minutes")
            );

        } catch (IndexOutOfBoundsException e) {
            List<Log> logs = logRepository.getLogs((User) Session.getInstance().getPrincipal());
            logs.forEach(log -> {
                System.out.println(log.getTime());
                System.out.println(log.getWorkout().name());
            });
        } catch (NumberFormatException e) {
            System.out.println("The given type isn't valid");
        } catch (InstanceNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
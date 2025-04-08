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

    public ViewLogCommand(
            CommandProcessor processor,
            String key,
            String information,
            LogRepository logRepository
    ) {
        super(processor, key, information);
        this.logRepository = logRepository;
    }

    @Override
    public void execute(String[] tokens) {
        try {

            getLog(tokens);

        } catch (IndexOutOfBoundsException e) {

            getLogs();

        } catch (NumberFormatException e) {

            System.out.println("\nThe provided types aren't compatible\n");

        } catch (Exception e) {

            System.out.println("\n" + e.getMessage() + "\n");

        }
    }

    private void getLog(String[] tokens) throws InstanceNotFoundException {
        int i = Integer.parseInt(tokens[0]);
        User user = (User) Session.getInstance().getPrincipal();

        Log log = logRepository.getLog(i, user);

        System.out.println("\n" + log.getAllInformation() + "\n");
    }

    private void getLogs() {
        User user = (User) Session.getInstance().getPrincipal();

        List<Log> logs = logRepository.getLogs(user);

        logs.forEach(log -> System.out.print("\n" + log.getSummary() + "\n\n"));
    }
}
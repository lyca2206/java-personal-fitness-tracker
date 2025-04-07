package com.lyca2206.controller.commands;

import com.lyca2206.controller.application.Application;
import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;

public class ExitCommand extends Command {
    private final Application application;

    public ExitCommand(CommandProcessor processor, String key, String information, Application application) {
        super(processor, key, information);
        this.application = application;
    }

    @Override
    public void execute(String[] tokens) {
        application.stop();
    }
}
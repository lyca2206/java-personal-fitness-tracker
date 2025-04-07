package com.lyca2206.controller.commands;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;

public class CreateWorkout extends Command {
    public CreateWorkout(CommandProcessor processor, String key, String information) {
        super(processor, key, information);
    }

    @Override
    public void execute(String[] tokens) {

    }
}
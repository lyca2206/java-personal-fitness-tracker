package com.lyca2206;

import com.lyca2206.controller.application.Application;
import com.lyca2206.controller.application.CommandLineApplication;
import com.lyca2206.controller.commands.ExitCommand;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.libraries.command.processor.MapCommandProcessor;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Reader reader = new InputStreamReader(System.in);
        CommandProcessor processor = new MapCommandProcessor(new HashMap<>());
        Application application = new CommandLineApplication(reader, processor);

        processor.changeCommands(List.of(
                new ExitCommand(processor, "exit", "exit - Exits the application", application)
        ));

        application.run();
    }
}
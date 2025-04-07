package com.lyca2206.libraries.command.processor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class MapCommandProcessor implements CommandProcessor {
    private final Map<String, Command> commands;

    public MapCommandProcessor(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public String getCommandInformation() {
        StringBuilder builder = new StringBuilder();
        
        commands.values().forEach(command ->
                builder
                        .append(command.getInformation())
                        .append("\n")
        );
        
        return builder.toString();
    }

    @Override
    public void execute(String[] tokens) {
        Command command = commands.get(tokens[0]);
        
        if (command == null) {
            return;
        }
        
        command.execute(
                Arrays.stream(tokens)
                        .skip(1)
                        .toArray(String[]::new)
        );
    }

    @Override
    public void changeCommands(Collection<Command> commands) {
        this.commands.clear();

        commands.forEach(command ->
                this.commands.put(command.getKey(), command)
        );
    }
}
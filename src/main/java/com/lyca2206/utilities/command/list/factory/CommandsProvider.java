package com.lyca2206.utilities.command.list.factory;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;

import java.util.Collection;

public abstract class CommandsProvider {
    protected final CommandsFactory commandsFactory;
    protected final CommandProcessor processor;
    protected final String key;

    public CommandsProvider(CommandsFactory commandsFactory, CommandProcessor processor, String key) {
        validateFactory(commandsFactory);
        validateProcessor(processor);
        validateKey(key);

        this.commandsFactory = commandsFactory;
        this.processor = processor;
        this.key = key;
    }

    private void validateFactory(CommandsFactory commandsFactory) {
        if (commandsFactory == null) {
            throw new IllegalArgumentException("The commandsFactory must not be a null value");
        }
    }

    private void validateProcessor(CommandProcessor processor) {
        if (processor == null) {
            throw new IllegalArgumentException("The command processor must not be a null value");
        }
    }

    private void validateKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("The given key must not be null or empty");
        }
    }

    public abstract Collection<Command> createCommands();
}
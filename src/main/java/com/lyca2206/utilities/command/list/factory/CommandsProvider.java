package com.lyca2206.utilities.command.list.factory;

import com.lyca2206.libraries.command.processor.Command;
import com.lyca2206.libraries.command.processor.CommandProcessor;

import java.util.Collection;

public abstract class CommandsProvider {
    protected final CommandsFactory commandsFactory;
    protected final CommandProcessor processor;
    protected final String key;

    public CommandsProvider(CommandsFactory commandsFactory, CommandProcessor processor, String key) {
        this.commandsFactory = commandsFactory;
        this.processor = processor;
        this.key = key;
    }

    public abstract Collection<Command> createCommands();
}
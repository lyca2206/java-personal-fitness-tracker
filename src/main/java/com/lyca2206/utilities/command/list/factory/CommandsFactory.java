package com.lyca2206.utilities.command.list.factory;

import com.lyca2206.libraries.command.processor.Command;

import java.util.Collection;

public interface CommandsFactory {
    Collection<Command> createCommands(String key);
}
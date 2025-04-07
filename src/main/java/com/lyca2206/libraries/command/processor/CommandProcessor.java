package com.lyca2206.libraries.command.processor;

import java.util.Collection;

public interface CommandProcessor {
    String getCommandInformation();
    void execute(String[] tokens);
    void changeCommands(Collection<Command> commands);
}
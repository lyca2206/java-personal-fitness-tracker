package com.lyca2206.libraries.command.processor;

public abstract class Command {
    protected final CommandProcessor processor;
    private final String key;
    private final String information;

    public Command(CommandProcessor processor, String key, String information) {
        validateProcessor(processor);
        validateKey(key);
        validateInformation(information);

        this.processor = processor;
        this.key = key;
        this.information = information;
    }

    private void validateProcessor(CommandProcessor processor) {
        if (processor == null) {
            throw new IllegalArgumentException("The command processor shouldn't be null");
        }
    }

    private void validateKey(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("The given key represents an invalid value of a command");
        }
    }

    private void validateInformation(String information) {
        if (information == null || information.isEmpty()) {
            throw new IllegalArgumentException("A command needs information about how it should be used. Example: print [message] - Prints a message in console");
        }
    }

    public abstract void execute(String[] tokens);

    public String getKey() {
        return key;
    }

    public String getInformation() {
        return information;
    }
}
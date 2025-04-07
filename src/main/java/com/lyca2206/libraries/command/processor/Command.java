package com.lyca2206.libraries.command.processor;

public abstract class Command {
    protected final CommandProcessor processor;
    private final String key;
    private final String information;

    public Command(CommandProcessor processor, String key, String information) {
        this.processor = processor;
        this.key = key;
        this.information = information;
    }

    public abstract void execute(String[] tokens);

    public String getKey() {
        return key;
    }

    public String getInformation() {
        return information;
    }
}
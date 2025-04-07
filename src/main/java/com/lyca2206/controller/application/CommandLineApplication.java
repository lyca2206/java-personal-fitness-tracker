package com.lyca2206.controller.application;

import com.lyca2206.libraries.command.processor.CommandProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class CommandLineApplication implements Application{
    private final Reader reader;
    private final CommandProcessor processor;
    private boolean isRunning;

    public CommandLineApplication(Reader reader, CommandProcessor processor) {
        this.reader = reader;
        this.processor = processor;
        this.isRunning = true;
    }

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
            while (isRunning) {
                System.out.println(processor.getCommandInformation());

                String line = bufferedReader.readLine();
                if (line == null) { return; }

                String[] tokens = line.trim().split(" ");

                processor.execute(tokens);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        isRunning = false;
    }
}
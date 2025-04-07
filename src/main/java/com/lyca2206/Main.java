package com.lyca2206;

import com.lyca2206.controller.application.Application;
import com.lyca2206.controller.application.CommandLineApplication;
import com.lyca2206.libraries.command.processor.CommandProcessor;
import com.lyca2206.libraries.command.processor.MapCommandProcessor;
import com.lyca2206.utilities.command.list.factory.CommandsProvidersFactory;
import com.lyca2206.utilities.command.list.factory.providers.AdminCommandsProvider;
import com.lyca2206.utilities.command.list.factory.providers.RegularCommandsProvider;
import com.lyca2206.utilities.command.list.factory.providers.SignedOutCommandsProvider;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Reader reader = new InputStreamReader(System.in);
        CommandProcessor processor = new MapCommandProcessor(new HashMap<>());
        Application application = new CommandLineApplication(reader, processor);
        CommandsProvidersFactory factory = new CommandsProvidersFactory(new HashSet<>());

        factory.setProviders(List.of(
                new SignedOutCommandsProvider(
                        factory,
                        processor,
                        "signedOut",
                        application
                ),

                new AdminCommandsProvider(
                        factory,
                        processor,
                        "admin"
                ),

                new RegularCommandsProvider(
                        factory,
                        processor,
                        "regular"
                )
        ));

        processor.changeCommands(factory.createCommands("signedOut"));
        application.run();
    }
}
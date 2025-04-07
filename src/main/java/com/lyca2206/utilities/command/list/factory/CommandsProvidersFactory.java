package com.lyca2206.utilities.command.list.factory;

import com.lyca2206.libraries.command.processor.Command;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

public class CommandsProvidersFactory implements CommandsFactory {
    private final Set<CommandsProvider> providers;

    public CommandsProvidersFactory(Set<CommandsProvider> providers) {
        this.providers = providers;
    }

    @Override
    public Collection<Command> createCommands(String key) {
        Collection<Command> collection = new LinkedList<>();
        
        providers.forEach(provider -> {
            if (provider.key.equals(key)) {
                collection.addAll(provider.createCommands());
            }
        });
        
        return collection;
    }

    public void setProviders(Collection<CommandsProvider> providers) {
        this.providers.clear();
        this.providers.addAll(providers);
    }
}
package com.lyca2206.utilities.command.list.factory;

import com.lyca2206.libraries.command.processor.Command;

import java.util.Collection;
import java.util.Set;
import java.util.function.Supplier;

public class CommandsProvidersFactory implements CommandsFactory {
    private final Set<CommandsProvider> providers;
    private final Supplier<Collection<Command>> collectionSupplier;

    public CommandsProvidersFactory(Set<CommandsProvider> providers, Supplier<Collection<Command>> collectionSupplier) {
        this.providers = providers;
        this.collectionSupplier = collectionSupplier;
    }

    @Override
    public Collection<Command> createCommands(String key) {
        Collection<Command> collection = collectionSupplier.get();
        
        providers.forEach(provider -> {
            if (provider.key.equalsIgnoreCase(key)) {
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
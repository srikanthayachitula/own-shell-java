package com.shell.models;

import com.shell.util.CommandRegistry;

import java.util.concurrent.atomic.AtomicBoolean;

public class ExitCommand implements ICommand {
    private final static ICommand instance = new ExitCommand();
    static  {
        CommandRegistry.register(instance);
    }

    public boolean execute(ShellContext shellContext) {
        shellContext.setShouldRun(false);
        return true;
    }

    public   ICommand getInstance() {
        return instance;
    }

    public String  getSignature() {
        return "exit";
    }

    @Override
    public boolean matches(String commandLineRequest) {
        return getSignature().equals(commandLineRequest);
    }


}

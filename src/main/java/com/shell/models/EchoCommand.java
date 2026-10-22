package com.shell.models;

import java.util.concurrent.atomic.AtomicBoolean;

public class EchoCommand implements ICommand{
    private static ICommand INSTANCE = new EchoCommand();
    @Override
    public boolean execute(ShellContext shellContext) {
        System.out.println(shellContext.getCurrentCommandRequest().substring(5));
        return true;
    }

    @Override
    public ICommand getInstance() {
        return INSTANCE;
    }


    @Override
    public String getSignature() {
        return "echo";
    }

    @Override
    public boolean matches(String commandLineRequest) {
        return commandLineRequest.startsWith(getSignature() + " ");
    }
}

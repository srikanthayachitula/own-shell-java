package com.shell.models;

import java.util.concurrent.atomic.AtomicBoolean;

public class EchoCommand implements ICommand{
    private static ICommand INSTANCE = new EchoCommand();
    @Override
    public boolean execute(CommandRequest commandRequest, AtomicBoolean shouldRun) {
        System.out.println("\n"+ commandRequest.getRequest().substring(5));
        return true;
    }

    @Override
    public ICommand getInstance() {
        return INSTANCE;
    }


    @Override
    public String getSignature() {
        return "echo ";
    }

    @Override
    public boolean matches(String commandLineRequest) {
        return commandLineRequest.startsWith(getSignature());
    }
}

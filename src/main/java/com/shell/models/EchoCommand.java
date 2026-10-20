package com.shell.models;

import java.util.concurrent.atomic.AtomicBoolean;

public class EchoCommand implements ICommand{
    @Override
    public boolean execute(AtomicBoolean shouldRun) {
        return true;
    }

    @Override
    public ICommand getInstance() {
        return null;
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

package com.shell.models;

import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;

public class PWDCommand implements ICommand {

    private ICommand instance = new PWDCommand();
    @Override
    public boolean execute(CommandRequest request, AtomicBoolean shouldRun) {
       System.out.println(Paths.get("").toAbsolutePath().toString());
       return true;
    }

    @Override
    public ICommand getInstance() {
        return instance;
    }

    @Override
    public String getSignature() {
        return "pwd";
    }

    @Override
    public boolean matches(String commandLineRequest) {
        return getSignature().equals(commandLineRequest);
    }
}

package com.shell.models;

import java.util.concurrent.atomic.AtomicBoolean;

public class ChangeDirectoryCommand implements ICommand {
    @Override
    public boolean execute(ShellContext shellContext) {
        shellContext.setCurrentWorkingDirectory(shellContext.getCurrentWorkingDirectory().resolve(getRequestPath(shellContext)).normalize());
        return true;
    }

    private String getRequestPath(ShellContext shellContext) {
        return shellContext.getCurrentCommandRequest().substring(3);
    }

    @Override
    public ICommand getInstance() {
        return null;
    }

    @Override
    public String getSignature() {
        return "cd";
    }

    @Override
    public boolean matches(String commandLineRequest) {
        return commandLineRequest.startsWith(getSignature());
    }
}

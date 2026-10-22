package com.shell.models;

public class PWDCommand implements ICommand {

    private static final ICommand instance = new PWDCommand();
    @Override
    public boolean execute(ShellContext shellContext) {
       System.out.println(shellContext.getCurrentWorkingDirectory().toAbsolutePath());
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

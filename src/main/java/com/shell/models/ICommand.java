package com.shell.models;

import java.util.concurrent.atomic.AtomicBoolean;

public interface ICommand {
    public abstract boolean execute(AtomicBoolean shouldRun);
    public abstract ICommand getInstance();
    public abstract String getSignature();
    public abstract boolean matches(String commandLineRequest);
}

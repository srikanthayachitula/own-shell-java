package com.shell.models;

import com.shell.util.CommandRegistry;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class TypeCommand implements ICommand {
    private static final  TypeCommand INSTANCE = new TypeCommand();

    @Override
    public boolean execute(CommandRequest request, AtomicBoolean shouldRun) {
        String command = request.getRequest().substring(5);
        if (CommandRegistry.isCommandRegistered(command)) {
            System.out.println(command + " is a shell builtin");
        }
        else {
                String executableFile =  CommandRegistry.isFileExecutable(command);
                if (!executableFile.isEmpty()) {
                   System.out.printf("%s is %s%n", command, executableFile);
                   return true;
               }
           }

           System.out.println(command + ": not found");
            return true;


    }

    @Override
    public ICommand getInstance() {
        return INSTANCE;
    }

    @Override
    public String getSignature() {
        return "type";
    }

    @Override
    public boolean matches(String commandLineRequest) {
        return commandLineRequest.startsWith("type");
    }
}

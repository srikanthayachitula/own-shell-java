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
           String PATH = System.getenv("PATH");
           String [] paths = PATH.split(":");
           for (String path : paths) {
               File file = new File(path + "/" + command);
               if (file.exists() && file.canExecute()) {
                   System.out.printf("%s is %s%n", command, file.getAbsolutePath());
                   return true;
               }
           }

           System.out.println(command + ": not found");
        }

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

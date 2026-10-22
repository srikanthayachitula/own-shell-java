package com.shell.util;

import com.shell.models.ICommand;
import com.shell.models.ShellContext;

import java.io.File;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

public class CommandRegistry {
    private static final ConcurrentHashMap<String, ICommand> commands = new ConcurrentHashMap<>();
    public static ICommand getInstance(String command) {
        return  commands.getOrDefault(command, null);
    }

    public static void register(ICommand commandInstance) {
        commands.put(commandInstance.getSignature(), commandInstance);
    }

    public static boolean isCommandRegistered(String command) {
        return commands.containsKey(command);
    }

    public static void autoRegisterAllCommands() {
        ServiceLoader.load(ICommand.class).forEach(CommandRegistry::register);
    }

    public static ICommand getMatchingCommand(ShellContext shellContext) {
        return commands
                .values().
                stream().
                filter(command -> command
                        .matches(shellContext.getCurrentCommandRequest()))
                .findFirst()
                .orElse(null);
    }

    public static String isFileExecutable(String fileName) {
        String PATH = System.getenv("PATH");
        String [] paths = PATH.split(":");
        for (String path : paths) {
            File file = new File(path + "/" + fileName);
            if (file.exists() && file.canExecute())  return file.getAbsolutePath();
        }

        return "";
    }



}

package com.shell.util;

import com.shell.models.CommandRequest;
import com.shell.models.ICommand;

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

    public static void populateCommand(CommandRequest commandRequest) {
      commandRequest
              .setCommand(commands
                      .values().
                      stream().
                      filter(command -> command
                              .matches(commandRequest.getRequest()))
               .findFirst()
                      .orElse(null));

    }
}

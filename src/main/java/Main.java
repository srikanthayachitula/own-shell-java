import com.shell.Constants.Messages;
import com.shell.models.ShellContext;
import com.shell.util.CommandRegistry;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) throws Exception {
        CommandRegistry.autoRegisterAllCommands();
        ShellContext shellContext = ShellContext.builder().build();
        do {
            renderDefaultCommandMessage();
            String commandLineRequest = readInput();
            shellContext.handleRequest(commandLineRequest);
        }
        while (shellContext.getShouldRun());

    }


    private static boolean isValidCommand(String command) {
        return  CommandRegistry.isCommandRegistered(command);
    }

    private static void renderDefaultCommandMessage() {
        System.out.printf(Messages.INITIAL_MESSAGE + "\n");
        System.out.flush();
    }
    private static String readInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}

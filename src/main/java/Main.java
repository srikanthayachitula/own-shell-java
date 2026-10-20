import com.shell.models.CommandRequest;
import com.shell.util.CommandRegistry;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) throws Exception {
        // TODO: Uncomment the code below to pass the first stage
        CommandRegistry.autoRegisterAllCommands();
        AtomicBoolean shouldRun = new AtomicBoolean(true);
        do {
            System.out.print("$ ");
            String commandLineRequest = readInput();
            CommandRequest commandRequest = CommandRequest.builder().request(commandLineRequest).build();
            CommandRegistry.populateCommand(commandRequest);
            if (commandRequest.getCommand() != null) {
               commandRequest.getCommand().execute(commandRequest, shouldRun);
            }
            else {
                System.out.printf("%s: command not found\n", commandLineRequest);
            }
        }
        while (shouldRun.get());

    }


    private static boolean isValidCommand(String command) {
        return  CommandRegistry.isCommandRegistered(command);
    }

    private static String readInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}

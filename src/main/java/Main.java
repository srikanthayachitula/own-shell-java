import com.shell.models.ICommand;
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
            String command = readInput();
            if (isValidCommand(command)) {
                ICommand commandInstance = CommandRegistry.getInstance(command);
                commandInstance.execute(shouldRun);
            }
            else {
                System.out.printf("%s: command not found\n", command);
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

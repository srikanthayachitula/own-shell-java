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
            String commandLineRequest = readInput();
            CommandRequest commandRequest = CommandRequest.builder().request(commandLineRequest).build();
            CommandRegistry.populateCommand(commandRequest);
            if (commandRequest.getCommand() != null) {
               commandRequest.getCommand().execute(commandRequest, shouldRun);
            }
            else {
                String [] commands = commandLineRequest.split(" ");
                String executableFile = CommandRegistry.isFileExecutable(commands[0]);
                if (!executableFile.isEmpty()) {
                    try {
                        //commands[0] = executableFile;
                        ProcessBuilder process = new  ProcessBuilder(commands);
                        process.inheritIO();
                        process.start().waitFor();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {

                    }
                }
                else System.out.printf("%s: command not found\n", commandLineRequest);
            }
        }
        while (shouldRun.get());

    }


    private static boolean isValidCommand(String command) {
        return  CommandRegistry.isCommandRegistered(command);
    }

    private static String readInput() {
        System.out.print("$ ");
        System.out.flush();
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}

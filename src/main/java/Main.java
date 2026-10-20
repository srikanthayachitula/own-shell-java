import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) throws Exception {
        // TODO: Uncomment the code below to pass the first stage
        AtomicBoolean shouldRun = new AtomicBoolean(true);
        do {
            System.out.print("$ ");
            String command = readInput();
            if (isValidCommand(command)) {

            }
            else {
                System.out.printf("%s: command not found\n", command);
            }
        }
        while (shouldRun.get());

    }


    private static boolean isValidCommand(String command) {
        return  false;
    }

    private static String readInput() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
}

import java.util.concurrent.ConcurrentHashMap;

public class CommandRegistry {
    private static final ConcurrentHashMap<String,ICommand> commands = new ConcurrentHashMap<>();
    public static ICommand getInstance(String command) {
        return  commands.getOrDefault(command, null);
    }

    public static void register(ICommand commandInstance) {
        commands.put(commandInstance.getSignature(), commandInstance);
    }
}

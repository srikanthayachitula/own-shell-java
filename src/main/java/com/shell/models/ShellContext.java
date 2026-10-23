package com.shell.models;

import com.shell.models.ICommand;
import com.shell.util.CommandRegistry;
import lombok.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicBoolean;
import static com.shell.Constants.Messages.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShellContext {
    @Builder.Default
    private AtomicBoolean shouldRun = new AtomicBoolean(true);
    @Builder.Default
    private Path currentWorkingDirectory = Paths.get("");
    private String currentCommandRequest;
    private ICommand currentCommand;
    private String errorMessage;
    @Builder.Default
    private Instant sessionStartTime = Instant.now();
    public void populateCommand() {

        ICommand command = (CommandRegistry.getMatchingCommand(this));
        if (command != null) {
            setCurrentCommand(command);
        }
        else  {
            setCurrentCommand(null);
            setErrorMessage(COMMAND_NOT_FOUND);
        }
    }

    public void setCurrentCommandRequest(String request) {
        this.currentCommandRequest = request;
        populateCommand();
    }

    public boolean getShouldRun() {
        return shouldRun.get();
    }
    public void setShouldRun(boolean shouldRun) {
        this.shouldRun.set(shouldRun);
    }


    public void handleRequest(String commandLineRequest) {
        this.setCurrentCommandRequest(commandLineRequest);
        if (this.getCurrentCommand() != null) {
            this.getCurrentCommand().execute(this);
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

}





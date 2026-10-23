package com.shell.models;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.shell.Constants.Messages.FILE_OR_FOLDER_MISSING;

public class ChangeDirectoryCommand implements ICommand {
    @Override
    public boolean execute(ShellContext shellContext) {
        Path expectedPath = shellContext.getCurrentWorkingDirectory().resolve(getRequestPath(shellContext)).normalize();
        if (Files.exists(expectedPath)) {
            shellContext.setCurrentWorkingDirectory(expectedPath);
        }
        else {
            System.out.println(expectedPath.toAbsolutePath() + ": " + FILE_OR_FOLDER_MISSING);
        }

        return true;
    }

    private String getRequestPath(ShellContext shellContext) {
        return shellContext.getCurrentCommandRequest().substring(3).replaceAll("^\"|\"$", "");
    }

    @Override
    public ICommand getInstance() {
        return null;
    }

    @Override
    public String getSignature() {
        return "cd";
    }

    @Override
    public boolean matches(String commandLineRequest) {
        return commandLineRequest.startsWith(getSignature());
    }
}

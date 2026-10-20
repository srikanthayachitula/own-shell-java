package com.shell.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommandRequest {
    private String request;
    private ICommand command;
    private boolean success;
    private String errorMessage;
}

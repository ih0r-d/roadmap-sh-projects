package com.ih0rd.models;

public enum Commands {
    ADD("add"),
    UPDATE("update"),
    DELETE("delete"),
    MARK_TODO("mark-todo"),
    MARK_IN_PROGRESS("mark-in-progress"),
    LIST("list"),
    LIST_TODO("list-todo"),
    LIST_IN_PROGRESS("list-in-progress"),
    LIST_DONE("list-done"),
    EXIT("exit"),
    HELP("help");

    Commands(String command) {
    }

    private String command;

    public String getCommand() {
        return command;
    }

    public static Commands fromString(String command) {
        for (Commands cmd : Commands.values()) {
            if (cmd.name().equals(command) || cmd.getCommand().equalsIgnoreCase(command)) {
                return cmd;
            }
        }
        throw new IllegalArgumentException("Unknown command: " + command);
    }

}

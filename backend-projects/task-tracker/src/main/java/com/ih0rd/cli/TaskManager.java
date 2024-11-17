package com.ih0rd.cli;


import com.ih0rd.models.Commands;
import com.ih0rd.models.TaskStatus;
import com.ih0rd.services.FilesFactory;
import com.ih0rd.services.TaskFactory;
import com.ih0rd.utils.TaskPrinter;

import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

import static com.ih0rd.models.Commands.EXIT;


public class TaskManager {

    private static final TaskFactory taskFactory = new TaskFactory();

    public static void run() {
        System.out.println("Welcome to the task manager!\nPlease, enter a command:");
        try (Scanner scanner = new Scanner(System.in)) {
            var inputString = scanner.next().trim();
            var command = Commands.valueOf(inputString.toUpperCase());

            while (!command.equals(EXIT)) {
                switch (command) {
                    case ADD -> {
//                        var optionalDescription = handleDescriptionValue(scanner);
//                        optionalDescription.ifPresent(description -> {
//                            var taskId = taskFactory.create(description);
//                            System.out.printf("Output: Task added successfully (ID: %s)%n", taskId);
//                        });
                    }
                    case LIST -> {
                        System.out.println("TODO");
                    }
                    default -> System.out.println("Unknown command. Please try again.");
                }

            }

            System.out.println("Exiting the program...");
        }
    }

    public static void run(String... args) {
        System.out.println("Welcome to the task manager!\nPlease, enter a command:");
        var command = Commands.fromString(args[0].toUpperCase());
        var arg1 = args[1].toLowerCase();
        var arg2 = args[2].toLowerCase();

        while (!command.equals(EXIT)) {
            switch (command) {
                case ADD -> {
                    var taskId = taskFactory.create(arg1);
                    System.out.printf("Task added successfully (ID: %s)%n", taskId);
                    break;
                }
                case LIST -> taskFactory.printTasks(null);
                case LIST_DONE -> taskFactory.printTasks(TaskStatus.DONE);
                case LIST_IN_PROGRESS -> taskFactory.printTasks(TaskStatus.IN_PROGRESS);
                case LIST_TODO -> taskFactory.printTasks(TaskStatus.TODO);
                case MARK_TODO -> {
                    taskFactory.update(Long.valueOf(arg1), TaskStatus.TODO);
                    System.out.printf("Updated task with id '%s' status to '%s')%n", arg1, TaskStatus.TODO.name());
                }
                case MARK_IN_PROGRESS -> {
                    taskFactory.update(Long.valueOf(arg1), TaskStatus.IN_PROGRESS);
                    System.out.printf("Updated task with id '%s' status to '%s')%n", arg1, TaskStatus.IN_PROGRESS.name());
                }
                case UPDATE -> {
                    taskFactory.update(Long.valueOf(arg1), arg2);
                    System.out.printf("Updated task with id '%s')%n", arg1);
                }
                case DELETE -> {
                    taskFactory.delete(Long.valueOf(arg1));
                    System.out.printf("Deleted task by id '%s')%n", arg1);
                }
                case HELP -> {
                    System.out.println("Supported commands:" + Arrays.toString(Commands.values()));
                }
                default -> System.out.println("Unknown command. Please try again.");
            }
        }

    }


    private static void handleArguments(String[] args) {
        var command = Commands.valueOf(args[0].toUpperCase());
        var arg1 = args[1].toLowerCase();
        var arg2 = args[2].toLowerCase();

    }

    private static Optional<Long> handleLongValue(String inputString) {
        try {
            return Optional.of(Long.parseLong(inputString));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}


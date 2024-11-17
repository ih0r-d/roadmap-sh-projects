package com.ih0rd.utils;

import com.ih0rd.models.Task;

import java.util.Collections;
import java.util.Set;

public class TaskPrinter {
    public static void printTable(Set<Task> tasks) {
        String header = String.format("| %-5s | %-30s | %-15s | %-20s | %-20s |",
                "ID", "Description", "Status", "Created At", "Updated At");
        String separator = String.join("", Collections.nCopies(header.length(), "-"));

        System.out.println(separator);
        System.out.println(header);
        System.out.println(separator);

        for (Task task : tasks) {
            System.out.printf("| %-5d | %-30s | %-15s | %-20s | %-20s |%n",
                    task.getId(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getCreatedAt(),
                    task.getUpdatedAt());
        }
        System.out.println(separator);
    }
}

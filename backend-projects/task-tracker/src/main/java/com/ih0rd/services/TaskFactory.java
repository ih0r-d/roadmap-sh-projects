package com.ih0rd.services;

import com.ih0rd.models.Task;
import com.ih0rd.models.TaskStatus;
import com.ih0rd.utils.TaskPrinter;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class TaskFactory {

    private final AtomicLong idCounter;

    private final FilesFactory filesFactory;

    private Set<Task> tasks;

    public TaskFactory() {
        this.idCounter = new AtomicLong(1);
        this.filesFactory = new FilesFactory();
        this.tasks = this.filesFactory.readFromFile();
    }

    private Optional<Task> getFirst(Long id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();
    }


    public long create(String description) {
        var createdTask = new Task(idCounter.getAndIncrement(), description, TaskStatus.TODO, LocalDateTime.now(), null);
        System.out.println("createdTask = " + createdTask);
        System.out.println(tasks);
        tasks.add(createdTask);
        filesFactory.writeToFile(tasks);
        return createdTask.getId();
    }

    public void update(Long id, String description) {
        getFirst(id).ifPresent(task -> {
            var updatedTask = new Task(id, description, task.getStatus(), task.getCreatedAt(), LocalDateTime.now());
            tasks.remove(task);
            tasks.add(updatedTask);
        });
        filesFactory.writeToFile(tasks);
    }

    public void update(Long id, TaskStatus status) {
        getFirst(id).ifPresent(task -> {
            var updatedTask = new Task(id, task.getDescription(), status, task.getCreatedAt(), LocalDateTime.now());
            tasks.remove(task);
            tasks.add(updatedTask);
        });
        filesFactory.writeToFile(tasks);
    }


    public void delete(Long id) {
        getFirst(id).ifPresent(tasks::remove);
        filesFactory.writeToFile(tasks);
    }

    public Set<Task> findAllByStatus(TaskStatus status) {
        if (status == null) {
            return tasks;
        }
        return tasks.stream()
                .filter(task -> task.getStatus().equals(status))
                .collect(Collectors.toSet());
    }

    public void printTasks(TaskStatus status) {
        var tasks = findAllByStatus(status);
        TaskPrinter.printTable(tasks);
    }




}

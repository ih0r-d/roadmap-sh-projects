package com.ih0rd.models;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.ih0rd.utils.TaskValidator.validateColumnsValues;

public class Task implements Comparable<Task> {
    private long id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task() {
    }

    public Task (Long id, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        validateColumnsValues(id, description, createdAt, updatedAt);
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int compareTo(Task task) {
        return Long.compare(this.id, task.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    @Override
    public String toString() {
        return String.format("| %-5d | %-30s | %-15s | %-20s | %-20s |",
                id, description, status, createdAt.toString(), updatedAt.toString());
    }

}

package com.ih0rd.utils;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskValidator {
    public static void validateColumnsValues(Long id, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        if (id <= 0) {
            throw new IllegalArgumentException("Task id must be greater than 0");
        }

        if (Objects.isNull(description) || description.isBlank() || description.length() > 150) {
            throw new IllegalArgumentException("Task description cannot be null or empty or description length greater than 150");
        }

        if (Objects.nonNull(updatedAt) && updatedAt.isBefore(createdAt)) {
            throw new IllegalArgumentException("Task updatedAt cannot be before createdAt");
        }
    }
}

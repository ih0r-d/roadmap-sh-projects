package com.ih0rd.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ih0rd.models.Task;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

import static com.ih0rd.utils.Constants.RESOURCES_PATH;

public class FilesFactory {

    private final static Path RESOURCES_FOLDER = Paths.get(RESOURCES_PATH);
    private final static String FILE_NAME = "tasks.json";
    private final static Path TASKS_FILE = Path.of("%s/%s".formatted(RESOURCES_FOLDER, FILE_NAME));

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            .registerModule(new JavaTimeModule());

    public void writeToFile(Set<Task> tasks) {
        try {
            if (!Files.exists(TASKS_FILE)) {
                Files.createFile(TASKS_FILE);
            }
            var jsonBytes = OBJECT_MAPPER.writeValueAsBytes(tasks);
            Files.write(TASKS_FILE, jsonBytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Set<Task> readFromFile() {

        try {
            if (Files.notExists(TASKS_FILE) || Files.size(TASKS_FILE) == 0) {
                return new HashSet<>();
            }
            var jsonString = Files.readString(TASKS_FILE);
            final TypeReference<Set<Task>> valueTypeRef = new TypeReference<>() {};
            return OBJECT_MAPPER.readValue(jsonString, valueTypeRef);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

package org.example.utils.constants;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

// since creating object mapper is heavy task. I created a singleton class to create the object mapper once and use it across the program
public class SingletonInstance {
    private final ObjectMapper objectMapper;
    public static SingletonInstance instance = null;
    public SingletonInstance () {
        objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
    }

    public static SingletonInstance getInstance() {
        if (instance == null) {
            instance = new SingletonInstance();
        }
        return instance;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}

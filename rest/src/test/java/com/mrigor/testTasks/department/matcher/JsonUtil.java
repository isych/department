package com.mrigor.testTasks.department.matcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

//import static com.mrigor.testTasks.department.json.JacksonObjectMapper.getMapper;

/**
 * User: gkislin
 * Date: 30.04.2014
 */
public class JsonUtil {

    private static ObjectMapper MAPPER=new ObjectMapper();


    public static <T> List<T> readValues(String json, Class<T> clazz) {
        ObjectReader reader = MAPPER.readerFor(clazz);
        try {
            return reader.<T>readValues(json).readAll();
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read array from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> T readValue(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid read from JSON:\n'" + json + "'", e);
        }
    }

    public static <T> String writeValue(T obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Invalid write to JSON:\n'" + obj + "'", e);
        }
    }
}

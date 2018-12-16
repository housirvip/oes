package vip.housir.base.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author housirvip
 */
@Slf4j
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    @Contract(value = "null -> null", pure = true)
    public static String convertToString(Object object) {

        if (object == null) {
            return null;
        }

        String result = null;
        try {
            result = mapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error("json convert to string failed", e);
        }

        return result;
    }

    @Contract(value = "null, _ -> null", pure = true)
    public static <T> T convertToObj(String json, Class<T> c) {

        if (json == null) {
            return null;
        }

        T result = null;
        try {
            result = mapper.readValue(json, c);
        } catch (IOException e) {
            log.error("json convert to obj failed", e);
        }

        return result;
    }

    @Contract(value = "null, _ -> null", pure = true)
    public static <T> List<T> convertToList(String json, Class<T> t) {

        if (json == null) {
            return null;
        }

        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, t);

        List<T> result = null;
        try {
            result = mapper.readValue(json, javaType);
        } catch (IOException e) {
            log.error("json convert to list failed", e);
        }

        return result;
    }

    @Contract(value = "null, _, _ -> null", pure = true)
    public static <K, V> Map<K, V> convertToMap(String json, Class<K> k, Class<V> v) {

        if (json == null) {
            return null;
        }

        JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, k, v);

        Map<K, V> result = null;
        try {
            result = mapper.readValue(json, javaType);
        } catch (IOException e) {
            log.error("json convert to map failed", e);
        }

        return result;
    }
}

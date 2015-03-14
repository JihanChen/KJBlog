package org.kymjs.blog.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by lody  on 2015/3/13.
 */


public class JsonParser {
    private static final Gson G = new Gson();

    private JsonParser() {

    }

    /**
     * @param json
     * @param classOfT
     * @param <T>
     * @return
     */
    public static <T> T fromJsonObject(String json, Class<T> classOfT) {
        return G.fromJson(json, classOfT);
    }

    /**
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String toJsonObject(T t) {
        return G.toJson(t);
    }

    /**
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> fromJsonArray(String json, Type type) {
        return G.fromJson(json, type);
    }

    /**
     * @param list
     * @param type
     * @param <T>
     * @return
     */
    public static <T> String toJsonArray(List<T> list, Type type) {
        return G.toJson(list, type);
    }
}

package ua.juniffiro.currency.api.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.HashSet;
import java.util.Set;

/**
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 * ( Created ) ( by ) ( @juniffiro )
 * 27/02/2023
 * +-+-+-+-+-+ +-+-+ +-+-+-+-+-+-+-+-+-+
 */
public class JsonUtils {

    /*
    Use Google GSON for working with JSON.
     */
    public static Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /**
     * Get JSON objects from JSON array.
     *
     * @param array
     *        JSON array.
     *
     * @return Set of JSON objects.
     */
    public static Set<JsonObject> jsonObjectsFromArray(JsonArray array) {
        Set<JsonObject> objects = new HashSet<>();
        for (int i = 0; i < array.size(); i++) {
            JsonObject obj = array.get(i).getAsJsonObject();
            objects.add(obj);
        }
        return objects;
    }
}

package us.devs.ingrosware.traits;

import com.google.gson.JsonObject;

/**
 * made for Ingros
 *
 * @author Brennan
 * @since 6/13/2020
 **/
public interface Configable {
    void save(JsonObject destination);

    void load(JsonObject source);
}

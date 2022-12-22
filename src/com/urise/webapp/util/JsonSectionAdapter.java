package com.urise.webapp.util;

import com.google.gson.*;

import java.lang.reflect.Type;

public class JsonSectionAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {
    private static  final String CLASSNAME = "CLASSNAME";
    private static  final String INSTANCE = "INSTANCE";


    @Override
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive primitive = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = primitive.getAsString();

        try {
            Class clazz = Class.forName(className);
            return context.deserialize(jsonObject.get(INSTANCE), clazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JsonElement serialize(T section, Type type, JsonSerializationContext context) {
        JsonObject returnValue = new JsonObject();
        returnValue.addProperty(CLASSNAME, section.getClass().getName());
        JsonElement element = context.serialize(section);
        returnValue.add(INSTANCE, element);
        return returnValue;
    }
}

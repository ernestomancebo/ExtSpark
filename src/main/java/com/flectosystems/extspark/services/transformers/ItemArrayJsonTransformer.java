package com.flectosystems.extspark.services.transformers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import spark.ResponseTransformer;

/**
 * Created by Ernesto Mancebo T on 3/3/15.
 */
public class ItemArrayJsonTransformer implements ResponseTransformer {

    Gson gson = new Gson();

    @Override
    public String render(Object model) throws Exception {
        JsonObject jsonObject = new JsonObject();

        // Handle the Array
        String itemArray = gson.toJson(model);
        JsonElement itemArrayElement = new JsonParser().parse(itemArray);
        jsonObject.add("item_array", itemArrayElement);

        // Put the success thing.
        jsonObject.addProperty("success", true);

        return jsonObject.toString();
    }
}

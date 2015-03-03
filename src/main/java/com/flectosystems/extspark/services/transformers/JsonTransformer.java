package com.flectosystems.extspark.services.transformers;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by Ernesto Mancebo T on 3/3/15.
 */
public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) throws Exception {
        return gson.toJson(model);
    }
}

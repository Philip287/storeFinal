package com.suprun.store.util.impl;

import com.google.gson.GsonBuilder;
import com.suprun.store.entity.Card;
import com.suprun.store.util.JsonParserUtil;
import com.google.gson.Gson;

public class JsonParserUtilImpl implements JsonParserUtil {

    private static JsonParserUtil instance;

    public static JsonParserUtil getInstance() {
        if (instance == null) {
            instance = new JsonParserUtilImpl();
        }
        return instance;
    }

    @Override
    public Card parse(String jsonCard) {
        Gson gson = new GsonBuilder().create();
        Card card = gson.fromJson(jsonCard, Card.class);
        return card;
    }

}

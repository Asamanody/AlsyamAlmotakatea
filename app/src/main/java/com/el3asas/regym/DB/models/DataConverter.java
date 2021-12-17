package com.el3asas.regym.DB.models;

import androidx.annotation.Keep;
import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

@Keep
public class DataConverter {

    @TypeConverter
    public String fromArrayToString(boolean[] countryLang) {
        if (countryLang == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<boolean[]>() {}.getType();
        String json = gson.toJson(countryLang, type);
        return json;
    }

    @TypeConverter
    public boolean[] fromStringToArray(String countryLangString) {
        if (countryLangString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<boolean[]>() {}.getType();
        return gson.fromJson(countryLangString, type);
    }
}
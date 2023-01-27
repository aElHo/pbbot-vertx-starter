package com.elho.pbbot.utils;

import io.vertx.core.AsyncResult;
import io.vertx.core.json.JsonObject;

/**
 * @author zhuangyf
 */
public class ConfigUtil {

    public static JsonObject jsonObject;

    public static String get(String key){
        return jsonObject.getString(key);
    }
    public static String get(String key,String def){
        return jsonObject.getString(key,def);
    }

    public static Integer getInteger(String key){
        return jsonObject.getInteger(key);
    }

    public static Integer getInteger(String key,Integer def){
        return jsonObject.getInteger(key,def);
    }

    public static void initConfig(AsyncResult<JsonObject> jsonObjectAsyncResult) {
        ConfigUtil.jsonObject = jsonObjectAsyncResult.result();
    }
}

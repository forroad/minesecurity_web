package com.ycjw.minesecurity.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

    /**
     * 将对象转换为字符串
     * @param object
     * @return
     */
   public static String toJson(Object object){
         GsonBuilder gsonBuilder=new GsonBuilder();
         gsonBuilder.setPrettyPrinting();
         Gson gson=gsonBuilder.create();
         return gson.toJson(object);
   }

    /**
     * 将字符串转换为对象
     * @param json
     * @param tClass
     * @param <T>
     * @return
     */
   public static <T extends Object> T toObject(String json,Class<T> tClass){
       GsonBuilder gsonBuilder=new GsonBuilder();
       Gson gson=gsonBuilder.create();
       return (T)gson.fromJson(json,tClass);
   }

}

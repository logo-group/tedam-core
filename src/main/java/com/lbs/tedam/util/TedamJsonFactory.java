/*
 * Copyright 2014-2019 Logo Business Solutions
 * (a.k.a. LOGO YAZILIM SAN. VE TIC. A.S)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.lbs.tedam.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class TedamJsonFactory {

    private static Gson gson = new GsonBuilder().create();

    private TedamJsonFactory() {
        // TedamJsonFactory private constructor
    }

    public static String toJson(Object obj) {
        String json = gson.toJson(obj);
        return json;
    }

    public static <T> T fromJson(String json, Type type) {
        T object = gson.fromJson(json, type);
        return object;
    }

    public static <T> List<T> fromJsonList(String json, Type type) {
        List<T> list = gson.fromJson(json, createJavaUtilListParameterizedType(type));
        return list;
    }

    public static <K, V> Map<K, V> fromJsonMap(String json, Type firstElementType, Type secondElementType) {
        Map<K, V> list = gson.fromJson(json, createJavaUtilMapParameterizedType(firstElementType, secondElementType));
        return list;
    }

    public static ParameterizedType createJavaUtilListParameterizedType(final Type elementType) {
        return (ParameterizedType) TypeToken.getParameterized(List.class, elementType).getType();
    }

    public static ParameterizedType createJavaUtilMapParameterizedType(final Type firstElementType, final Type secondElementType) {
        return (ParameterizedType) TypeToken.getParameterized(Map.class, firstElementType, secondElementType).getType();
    }
}

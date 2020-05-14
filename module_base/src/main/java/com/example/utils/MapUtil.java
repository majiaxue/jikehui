package com.example.utils;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
    private static Map map;

    public static MapUtil getInstance() {
        map = new HashMap();
        return new MapUtil();
    }

    public MapUtil addParms(Object key, Object value) {
        map.put(key, value);
        return this;
    }

    public Map build() {
        return map;
    }
}

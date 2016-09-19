package framework.utils;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonUtils {
    public static Map<String, Object> toMap(String jsonString, boolean recursive) {
        return toMap(JSONObject.fromObject(jsonString), recursive);
    }

    public static Map<String, Object> toMap(JSONObject jsonObject, boolean recursive) {
        Map<String, Object> result = new HashMap<>();
        Iterator it = jsonObject.keys();
        while (it.hasNext()) {
            String key = String.valueOf(it.next());
            if (recursive && jsonObject.get(key) instanceof JSONObject)
                result.put(key, toMap((JSONObject) jsonObject.get(key), recursive));
            else
                result.put(key, jsonObject.get(key));
        }
        return result;
    }
}

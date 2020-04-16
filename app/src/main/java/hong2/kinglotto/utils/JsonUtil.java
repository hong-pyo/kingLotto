package hong2.kinglotto.utils;

import com.google.gson.Gson;

public class JsonUtil {
    private static Gson gson = new Gson();

    public static String getJsonString(Object o){
        return gson.toJson(o);
    }

    public static <T> T fromJson(String response, Class<T> classOfT) {
        return gson.fromJson(response, classOfT);
    }
}

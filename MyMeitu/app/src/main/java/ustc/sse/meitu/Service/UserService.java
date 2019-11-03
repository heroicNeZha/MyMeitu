package ustc.sse.meitu.Service;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

import ustc.sse.meitu.pojo.User;
import ustc.sse.meitu.utils.HttpUtil;

public class UserService {

    Type mapType = new TypeToken<Map<String, String>>() {
    }.getType();

    /**
     * 登录接口
     * /api/user/
     * {
     * "action" : "login",
     * "username": "<your name>",
     * "password": "<your password>"
     * }
     */
    public String Login(User user) {
        Map<String, String> data = new TreeMap<>();
        data.put("action", "login");
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());
        Gson gson = new Gson();
        String json = gson.toJson(data);
        String responseJson = HttpUtil.post(HttpUtil.BASE_URL + "/api/user/", json);
        System.out.println(responseJson);
        Map<String, String> response = gson.fromJson(responseJson, mapType);
        if (response.size() == 3) {
            return "success:" + response.get("token");
        } else {
            return "error:" + response.get("msg");
        }
    }

    public String Register(User user) {
        Map<String, String> data = new TreeMap<>();
        data.put("action", "register");
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());
        Gson gson = new Gson();
        String json = gson.toJson(data);
        String responseJson = HttpUtil.post(HttpUtil.BASE_URL + "/api/user/", json);
        Map<String, String> response = gson.fromJson(responseJson, mapType);
        if (response.size() == 3) {
            return "success:" + response.get("token");
        } else {
            return "error:" + response.get("msg");
        }
    }

}

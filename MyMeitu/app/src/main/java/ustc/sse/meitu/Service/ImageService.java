package ustc.sse.meitu.Service;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import ustc.sse.meitu.pojo.Image;
import ustc.sse.meitu.pojo.User;
import ustc.sse.meitu.utils.HttpUtil;

public class ImageService {

    Type mapType = new TypeToken<Map<String, String>>() {
    }.getType();

    public Map<String, String> upload(String token, List<Image> images) {
        Map<String, String> data = new TreeMap<>();
        data.put("token", token);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            for (int i = 0; i < images.size(); i++) {
                out.flush();
                Bitmap bitmap = images.get(i).getBitmap();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 30, out);
                data.put("img" + i + " " + images.get(i).getPath(), Arrays.toString(out.toByteArray()));
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String json = gson.toJson(data);
        String responseJson = HttpUtil.post(HttpUtil.BASE_URL + "/api/upload/", json);
        System.out.println(responseJson);
        Map<String, String> response = gson.fromJson(responseJson, mapType);
        if (response.get("errorcode") != null)
            return response;
        else
            return null;
    }

    public Map<String, String> list(String token) {
        Map<String, String> data = new TreeMap<>();
        data.put("token", token);
        Gson gson = new Gson();
        String json = gson.toJson(data);
        String responseJson = HttpUtil.post(HttpUtil.BASE_URL + "/api/geturl/", json);
        System.out.println(responseJson);
        Map<String, String> response = gson.fromJson(responseJson, mapType);
        if (response.get("errorcode") != null)
            return response;
        else
            return null;
    }
}

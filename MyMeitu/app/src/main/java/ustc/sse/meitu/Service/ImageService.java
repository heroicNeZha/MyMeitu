package ustc.sse.meitu.Service;

import android.graphics.Bitmap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.MultipartBody;
import ustc.sse.meitu.pojo.Image;
import ustc.sse.meitu.pojo.User;
import ustc.sse.meitu.utils.HttpUtil;

public class ImageService {

    Type mapType = new TypeToken<Map<String, String>>() {
    }.getType();

//    public Map<String, String> upload(String token, List<Image> images) {
//        Map<String, Object> data = new TreeMap<>();
//        HttpEntity entity;
//        File file;
//        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
//            for (int i = 0; i < images.size(); i++) {
//                out.flush();
//                file = new File(images.get(i).getPath());
//                images.get(i).getBitmap().compress(Bitmap.CompressFormat.JPEG, 30, out);
//                entity = MultipartEntityBuilder
//                        .create()
//                        .addBinaryBody("img " + i, file, ContentType.create("image/jpeg"), file.getName())
//                        .build();
//                data.put(images.get(i).getPath().split("0/", 2)[1], entity);
//            }
//            out.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        data.put("token", token);
//        Gson gson = new Gson();
//        String json = gson.toJson(data);
//        System.out.println(json);
//        String responseJson = HttpUtil.post(HttpUtil.BASE_URL + "/api/upload/", json);
//        System.out.println(responseJson);
//        Map<String, String> response = gson.fromJson(responseJson, mapType);
//        if (response.get("errorcode") != null)
//            return response;
//        else
//            return null;
//    }

    public Map<String, String> upload(String token, List<Image> images) {
        Gson gson = new Gson();
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addPart("token", new StringBody(token, ContentType.TEXT_PLAIN));
        for (Image image : images) {
            File file = new File(image.getPath());
            entityBuilder.addBinaryBody(image.getPath(), file, ContentType.create("image/jpeg"), file.getName());
        }
        String responseJson = null;
        try {
            responseJson = HttpUtil.post(HttpUtil.BASE_URL + "/api/upload/", entityBuilder.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

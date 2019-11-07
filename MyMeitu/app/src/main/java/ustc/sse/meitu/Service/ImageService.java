package ustc.sse.meitu.Service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.Gson;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import ustc.sse.meitu.listener.BitmapCallbackListener;
import ustc.sse.meitu.listener.ImageListCallbackListener;
import ustc.sse.meitu.pojo.Image;
import ustc.sse.meitu.utils.HttpUtils;

public class ImageService {

    private Gson gson;

    public ImageService() {
        gson = new Gson();
    }

    public Map<String, String> upload(String token, List<Image> images) {
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        entityBuilder.addPart("token", new StringBody(token, ContentType.TEXT_PLAIN));
        for (Image image : images) {
            File file = new File(image.getPath());
            entityBuilder.addBinaryBody(image.getPath(), file, ContentType.create("image/jpeg"), file.getName());
        }
        String responseJson = null;
        try {
            responseJson = HttpUtils.getInstance().post(HttpUtils.BASE_URL + "/api/upload/", entityBuilder.build());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(responseJson);
        Map<String, String> response = gson.fromJson(responseJson, HttpUtils.mapType);
        if (response.get("errorcode") == null)
            return response;
        else
            return null;
    }


    public void get(Image image, BitmapCallbackListener bitmapCallbackListener) {
        HttpUtils.getInstance().getWithOkhttp(HttpUtils.BASE_URL + image.getUrl(), new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                byte[] img = response.body().bytes();
                Bitmap bitmap = BitmapFactory.decodeByteArray(img, 0, img.length);
                bitmapCallbackListener.onSuccess(bitmap);
            }
        });
    }

    public void list(String token, ImageListCallbackListener imageListCallbackListener) {
        Map<String, String> data = new TreeMap<>();
        data.put("token", token);
        Gson gson = new Gson();
        String json = gson.toJson(data);
        HttpUtils.getInstance().postWithOkhttp(HttpUtils.BASE_URL + "/api/geturl/", json, new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseJson = response.body().string();
                System.out.println(responseJson);
                if (responseJson.contains("errorcode")) {
                    Map<String, String> responseMap = gson.fromJson(responseJson, HttpUtils.mapType);
                    imageListCallbackListener.onError("错误码:" + responseMap.get("errorcode") + "错误信息:" + responseMap.get("msg"));
                } else {
                    Map<String, ArrayList<Image>> responseMap = gson.fromJson(responseJson, HttpUtils.ImageArrayType);
                    imageListCallbackListener.onSuccess(responseMap.get("photo_list"));
                }
            }
        });
    }
}

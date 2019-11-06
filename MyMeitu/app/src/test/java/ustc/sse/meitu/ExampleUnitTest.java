package ustc.sse.meitu;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.Gson;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import ustc.sse.meitu.Service.ImageService;
import ustc.sse.meitu.Service.UserService;
import ustc.sse.meitu.pojo.Image;
import ustc.sse.meitu.pojo.User;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testUserService() {
        UserService userService = new UserService();
        User user = new User();
        user.setUsername("gggggggg");
        user.setPassword("tggggggggg");
        System.out.println(userService.Login(user).split(":", 2)[1]);
    }

    @Test
    public void testJson() {
        Map<String, String> data = new TreeMap<>();
        data.put("action", "login");
        data.put("username", "test");
        data.put("password", "test");
        Gson gson = new Gson();
        String json = gson.toJson(data);
        System.out.println(json);
    }

    @Test
    public void testUpload() {
        UserService userService = new UserService();
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        String token = userService.Login(user).split(":", 2)[1];
        Image image = new Image("/image1.jpeg", "test");
        ImageService imageService = new ImageService();
        ArrayList<Image> images = new ArrayList<>();
        images.add(image);
        Map<String, String> map = imageService.upload(token, images);
        System.out.println(map.toString());
    }

}
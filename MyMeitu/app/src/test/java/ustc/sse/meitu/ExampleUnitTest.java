package ustc.sse.meitu;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.Map;
import java.util.TreeMap;

import ustc.sse.meitu.Service.UserService;
import ustc.sse.meitu.pojo.User;

import static org.junit.Assert.*;

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
        user.setUsername("test");
        user.setPassword("test2");
        System.out.println(userService.Login(user).split(":",2)[1]);
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
}
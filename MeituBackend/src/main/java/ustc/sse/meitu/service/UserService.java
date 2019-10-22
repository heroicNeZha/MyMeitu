package ustc.sse.meitu.service;

import org.springframework.stereotype.Service;
import ustc.sse.meitu.pojo.User;

public interface UserService {
    User get(int id);

    Boolean login(User user);

    int add(User user);
}

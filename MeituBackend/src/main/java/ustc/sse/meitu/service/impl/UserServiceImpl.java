package ustc.sse.meitu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ustc.sse.meitu.mapper.UserMapper;
import ustc.sse.meitu.pojo.User;
import ustc.sse.meitu.service.UserService;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User get(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Boolean login(User user) {
        return null;
    }

    @Override
    public int add(User user) {
        return userMapper.insert(user);
    }
}

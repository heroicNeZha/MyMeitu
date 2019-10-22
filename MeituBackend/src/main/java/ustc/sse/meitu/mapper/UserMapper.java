package ustc.sse.meitu.mapper;

import java.util.List;
import ustc.sse.meitu.pojo.User;
import ustc.sse.meitu.pojo.UserExample;

public interface UserMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
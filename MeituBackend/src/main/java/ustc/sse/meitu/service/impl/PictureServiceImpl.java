package ustc.sse.meitu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ustc.sse.meitu.mapper.PictureMapper;
import ustc.sse.meitu.pojo.Picture;
import ustc.sse.meitu.pojo.PictureExample;
import ustc.sse.meitu.pojo.User;
import ustc.sse.meitu.service.PictureService;

import java.util.List;
@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    PictureMapper pictureMapper;

    @Override
    public int add(Picture picture) {
        return pictureMapper.insert(picture);
    }

    @Override
    public List<Picture> list(int uid) {
        PictureExample example = new PictureExample();
        example.createCriteria().andUidEqualTo(uid);
        example.setOrderByClause("uid desc");

        List<Picture> list = pictureMapper.selectByExample(example);
        return list;
    }

    @Override
    public Picture get(int id) {
        return pictureMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(Picture picture) {
        return pictureMapper.updateByPrimaryKey(picture);
    }

    @Override
    public int delete(int id) {
        return pictureMapper.deleteByPrimaryKey(id);
    }
}

package ustc.sse.meitu.service;

import org.springframework.stereotype.Service;
import ustc.sse.meitu.pojo.Picture;
import ustc.sse.meitu.pojo.User;

import java.util.List;

public interface PictureService {
    int add(Picture picture);

    List<Picture> list(int uid);

    Picture get(int id);

    int update(Picture picture);

    int delete(int id);
}

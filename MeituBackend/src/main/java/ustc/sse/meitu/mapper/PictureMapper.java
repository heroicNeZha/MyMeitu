package ustc.sse.meitu.mapper;

import java.util.List;
import ustc.sse.meitu.pojo.Picture;
import ustc.sse.meitu.pojo.PictureExample;

public interface PictureMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Picture record);

    int insertSelective(Picture record);

    List<Picture> selectByExample(PictureExample example);

    Picture selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);
}
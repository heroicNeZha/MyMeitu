package ustc.sse.meitu.listener;

import java.util.ArrayList;
import java.util.List;

import ustc.sse.meitu.pojo.Image;

public interface ImageListCallbackListener {
    void onSuccess(ArrayList<Image> imageList);

    void onError(String exception);
}

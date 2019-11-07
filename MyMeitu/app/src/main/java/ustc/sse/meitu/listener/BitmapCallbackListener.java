package ustc.sse.meitu.listener;

import android.graphics.Bitmap;

import java.util.ArrayList;

import ustc.sse.meitu.pojo.Image;

public interface BitmapCallbackListener {
    void onSuccess(Bitmap bitmap);

    void onError(String exception);
}

package ustc.sse.meitu.pojo;

import android.graphics.Bitmap;

public class Image {
    private String path;
    private Bitmap bitmap;
    private String text;
    private Boolean isDelete;

    public Image(String path, String text) {
        this.path = path;
        this.text = text;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

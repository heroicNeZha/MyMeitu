package ustc.sse.meitu.pojo;

import android.app.Application;

public class MyApplicationContext extends Application {
    PicData picData;

    String token;

    public PicData getPicData() {
        return picData;
    }

    public void setPicData(PicData picData) {
        this.picData = picData;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

package ustc.sse.meitu.pojo;

import android.app.Application;
import android.graphics.Bitmap;

public class PicData {
    /**
    * 方法描述  用于在各个View之间传递截图数据
    * @param  参数描述
    * @return  返回值描述
    */

    //裁剪框所在位置，用于给出裁剪部分
    float cutRectLeft ;
    float cutRectTop ;
    float cutRectRight ;
    float cutRectBottom ;
    //pagic视图所在位置，用于定位文字框
    float pagicLeft ;
    float pagicTop;
    float pagicRight;
    float pagicBottom;
    Bitmap mBase;   //正在处理的图片
    public float getPagicLeft() {
        return pagicLeft;
    }

    public void setPagicLeft(float pagicLeft) {
        this.pagicLeft = pagicLeft;
    }

    public float getPagicTop() {
        return pagicTop;
    }

    public void setPagicTop(float pagicTop) {
        this.pagicTop = pagicTop;
    }


    public float getPagicRight() {
        return pagicRight;
    }

    public void setPagicRight(float pagicRight) {
        this.pagicRight = pagicRight;
    }

    public float getPagicBottom() {
        return pagicBottom;
    }

    public void setPagicBottom(float pagicBottom) {
        this.pagicBottom = pagicBottom;
    }

    public Bitmap getmBase() {
        return mBase;
    }

    public void setmBase(Bitmap mBase) {
        this.mBase = mBase;
    }

    public float getCutRectLeft() {
        return cutRectLeft;
    }

    public void setCutRectLeft(float cutRectLeft) {
        this.cutRectLeft = cutRectLeft;
    }

    public float getCutRectTop() {
        return cutRectTop;
    }

    public void setCutRectTop(float cutRectTop) {
        this.cutRectTop = cutRectTop;
    }

    public float getCutRectRight() {
        return cutRectRight;
    }

    public void setCutRectRight(float cutRectRight) {
        this.cutRectRight = cutRectRight;
    }

    public float getCutRectBottom() {
        return cutRectBottom;
    }

    public void setCutRectBottom(float cutRectBottom) {
        this.cutRectBottom = cutRectBottom;
    }

}

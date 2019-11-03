package ustc.sse.meitu.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import ustc.sse.meitu.pojo.MyApplicationContext;
import ustc.sse.meitu.pojo.PicData;

public class cutView extends View {
    float downX;    //按下的X坐标
    float downY;    //按下的Y坐标
    boolean isLeft; //是否按在左边线
    boolean isRight;    //是否按在右边线
    boolean isTop;  //是否按在上边线
    boolean isBottom;   //是否按在下边线
    boolean isMove; //是否想要移动框
    boolean isXShrinkble = true;//判断X轴能否继续缩小
    boolean isYShrinkble = true;//判断Y轴能否继续缩小

    float rectLeft ;    //裁剪框左上X
    float rectRight ;   //裁剪框右下X
    float rectTop ;     //裁剪框左上Y
    float rectBottom ;  //裁剪框右下Y
    private int maxWidth;  //边界宽度
    private int maxHeight; //边界高度
    private Paint rectpaint;    //框画笔
    private Paint cornerpaint;  //角落画笔
    static PicData mRect;   //View间的共享数据



    public cutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public cutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public cutView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        /**
        * 方法描述  初始化裁剪框的位置，最大边界，画笔
        * @param [context] 参数描述
        * @return void 返回值描述
        */
        mRect = ((MyApplicationContext) context.getApplicationContext()).getPicData();
        rectLeft = mRect.getCutRectLeft();
        rectTop = mRect.getCutRectTop();
        rectRight = mRect.getCutRectRight();
        rectBottom = mRect.getCutRectBottom();
        maxWidth = (int) rectRight;
        maxHeight = (int) rectBottom;
        rectpaint = new Paint();
        rectpaint.setAntiAlias(true);
        rectpaint.setColor(Color.BLACK);
        rectpaint.setStyle(Paint.Style.STROKE);
        rectpaint.setStrokeWidth(3);
        cornerpaint = new Paint();
        cornerpaint.setAntiAlias(true);
        cornerpaint.setColor(Color.BLACK);
        cornerpaint.setStyle(Paint.Style.STROKE);
        cornerpaint.setStrokeWidth(10);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                downX = event.getX();
                downY = event.getY();

                if(downX >= rectLeft && downX <= rectRight && downY >= rectTop && downY <= rectBottom){
                    //判断手指是否在边线上，边线的两侧1/4长度范围内判定为在线上
                    //判断手指的范围在左面还是右面
                    int w = (int) ((rectRight - rectLeft)/4);
                    if ((downX >= rectLeft && downX <= rectLeft+w)||(downX <= rectLeft && downX >= rectLeft-w)) {
                        isLeft = true;
                    } else if((downX <= rectRight && downX >= rectRight - w)||(downX >= rectRight && downX <= rectRight + w)) {
                        isRight = true;
                    }
                    //判断手指的范围在上面还是下面
                    int h = (int) ((rectBottom - rectTop)/4);
                    if ((downY >= rectTop && downY <= rectTop+h)||(downY <= rectTop && downY >= rectTop-h)) {
                        isTop = true;
                    } else if ((downY <= rectBottom && downY >= rectBottom - h)||(downY >= rectBottom && downY <= rectBottom + h)){
                        isBottom = true;
                    }
                    //如果手指范围没有在任何边界位置, 认为用户是想拖拽框体
                    if (!isLeft && !isTop && !isRight && !isBottom) {
                        isMove = true;
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = event.getX();
                float moveY = event.getY();
                //得到手指移动距离
                float slideX = moveX - downX ;
                float slideY = moveY - downY;
                //是否移动裁剪框
                if (isMove) {
                    //同时改变四条边值, 达到左右移动的效果
                    rectLeft += slideX;
                    rectRight += slideX;
                    rectTop += slideY;
                    rectBottom += slideY;
                    //当出界时恢复到画框中
                    if(rectLeft<0){
                        rectRight -= rectLeft;
                        rectLeft = 0;
                    }
                    if(rectTop<0){
                        rectBottom -= rectTop;
                        rectTop = 0;
                    }
                    if(rectRight>maxWidth){
                        rectLeft -= (rectRight-maxWidth);
                        rectRight = maxWidth;
                    }
                    if(rectBottom>maxHeight){
                        rectTop -= (rectBottom-maxHeight);
                        rectBottom = maxHeight;
                    }
                    downX = moveX;
                    downY = moveY;
                }
                //当X轴可缩小且按在左或右边
                else if(isXShrinkble&&(isLeft||isRight)) {
                    if (isLeft) {
                        rectLeft += slideX;
                        if (rectLeft < 0) rectLeft = 0;
                    } else if (isRight) {
                        rectRight += slideX;
                        rectRight = (rectRight > maxWidth) ? maxWidth : rectRight;
                    }
                }
                //当Y轴可缩小且按在上或下边
                else if(isYShrinkble&&(isTop||isBottom)) {
                    if (isTop) {
                        rectTop += slideY;
                        if (rectTop < 0) rectTop = 0;
                    } else if (isBottom) {
                        rectBottom += slideY;
                        rectBottom = (rectBottom>maxHeight)?maxHeight:rectBottom;
                    }
                }
                //当不可继续缩小时判断是否放大
                else if(isEnlarging(slideX,slideY)){
                    if (isLeft) {
                        rectLeft += slideX;
                        if (rectLeft < 0) rectLeft = 0;
                    } else if (isRight) {
                        rectRight += slideX;
                        rectRight = (rectRight > maxWidth) ? maxWidth : rectRight;
                    }
                    if (isTop) {
                        rectTop += slideY;
                        if (rectTop < 0) rectTop = 0;
                    } else if (isBottom) {
                        rectBottom += slideY;
                        rectBottom = (rectBottom>maxHeight)?maxHeight:rectBottom;
                    }
                    }
                //当上下或左右距离达到300时不可继续缩小
                if((rectBottom-rectTop)>300){
                    isYShrinkble = true;
                }else {
                    isYShrinkble = false;
                }
                if((rectRight-rectLeft)>300){
                    isXShrinkble = true;
                }else {
                    isXShrinkble = false;
                }
                    invalidate();
                    downX = moveX;
                    downY = moveY;
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                isLeft = false;
                isRight = false;
                isTop = false;
                isBottom = false;
                isMove = false;
                break;
        }
        return true;
    }

    private boolean isEnlarging(float slideX,float slideY){
        /**
        * 方法描述  判断是否正在将裁剪框放大
        * @param [slideX, slideY] 参数描述
        * @return boolean 返回值描述
        */
        if(isTop&&slideY<=0){
            return true;
        }
        if(isBottom&&slideY>=0){
            return true;
        }
        if(isLeft&&slideX<=0){
            return true;
        }
        if(isRight&&slideX>=0){
            return true;
        }
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
        * 方法描述  自适应改变View大小
        * @param [widthMeasureSpec, heightMeasureSpec] 参数描述
        * @return void 返回值描述
        */
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int mWidth =(int) mRect.getCutRectRight();
        int mHeight = (int) mRect.getCutRectBottom();

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //绘制裁剪框
        canvas.drawRect(rectLeft, rectTop, rectRight, rectBottom, rectpaint);
        //绘制四个角
        drawCorner(canvas, rectLeft, rectTop, rectRight, rectBottom);
    }

    private void drawCorner(Canvas canvas, float left, float top, float right, float bottom) {
    /**
    * 方法描述  绘制裁剪框的四个角
    * @param [canvas, left, top, right, bottom] 参数描述
    * @return void 返回值描述
    */
        //取得角的边长
        float cornerLength = Math.min((bottom - top),(right-left))/4;

        //绘制四个角
        canvas.drawLine(left, top, left, top+cornerLength, cornerpaint);
        canvas.drawLine(left, top, left+cornerLength, top, cornerpaint);

        canvas.drawLine(left, bottom, left, bottom-cornerLength, cornerpaint);
        canvas.drawLine(left, bottom, left+cornerLength, bottom, cornerpaint);

        canvas.drawLine(right, top, right, top+cornerLength, cornerpaint);
        canvas.drawLine(right, top, right-cornerLength, top, cornerpaint);

        canvas.drawLine(right, bottom, right, bottom-cornerLength, cornerpaint);
        canvas.drawLine(right, bottom, right-cornerLength, bottom, cornerpaint);
    }

    public void cropImg(){
        /**
        * 方法描述  按下确定后调用此方法获取裁剪框内部分并放在共享数据中
        * @param [] 参数描述
        * @return void 返回值描述
        */
        Bitmap croppedImg = Bitmap.createBitmap(mRect.getmBase(), (int)rectLeft, (int)rectTop, (int)(rectRight-rectLeft), (int)(rectBottom-rectTop), new Matrix(), false);
        mRect.setmBase(croppedImg);
    }
}
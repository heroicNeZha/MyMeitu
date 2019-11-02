package ustc.sse.meitu.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.widget.AppCompatImageView;

import ustc.sse.meitu.R;
import ustc.sse.meitu.pojo.picData;

public class pagicView extends AppCompatImageView {
    private Matrix mMatrix;
    public Bitmap mBase;    //基础图片，用来恢复
    private float mLastX;   //画线按下时的X坐标，作为起点
    private float mLastY;   //画线按下时的Y坐标，作为起点
    private Paint mLinePaint;   //画笔
    private Canvas mDrawLineCanvas; //画布
    private float mPreScale;    //图片需要改变的规模参数，用来适应Layout
    public float mLayoutWidth;     //屏幕宽度，用来改变图片和所在Layout大小
    public float mLayoutHeight;    //屏幕高度，用来改变图片和所在Layout大小
    public static picData mRect;      //传递共享数据,主要对其中的图片进行操作

    private enum mState {
        START,
        DRAW,
        ROTATE,
        CROP,
        TEXT
    }   //状态枚举，用来表示当前执行的功能

    private mState mCurrentState = mState.START;    //当前执行的功能

    public pagicView(Context context) {
        super(context);
        initView();
    }
    public pagicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public pagicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        /**
        * 方法描述 初始化各项参数
        * @param [] 参数描述
        * @return void 返回值描述
        */

        //获取屏幕大小
        Resources resources = this.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        //宽度与屏幕相等
        mLayoutWidth= dm.widthPixels;
        //高度是屏幕的4/5
        mLayoutHeight= dm.heightPixels;

        //需要操作的图片
//        mBase = scaleBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bot).copy(Bitmap.Config.ARGB_8888, true));

        mRect = (picData)getContext().getApplicationContext();
        mBase = scaleBitmap(mRect.getmBase());
        mRect.setCutRectLeft(0);
        mRect.setCutRectTop(0);
        mRect.setCutRectRight(mBase.getWidth());
        mRect.setCutRectBottom(mBase.getHeight());
        mRect.setmBase(mBase);

        mDrawLineCanvas = new Canvas(mRect.getmBase());
        mMatrix = new Matrix();
        initPaint();
    }
    public void reInit(){
        /**
        * 方法描述  改变图片后调用此方法重新加载pagicView
        * @param [] 参数描述
        * @return void 返回值描述
        */
        mRect.setmBase(scaleBitmap(mRect.getmBase()));
        mDrawLineCanvas = new Canvas(mRect.getmBase());
        mRect.setCutRectLeft(0);
        mRect.setCutRectTop(0);
        mRect.setCutRectRight(mRect.getmBase().getWidth());
        mRect.setCutRectBottom(mRect.getmBase().getHeight());
        mBase = mRect.getmBase();
        //一定要重新更新布局参数
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        this.setLayoutParams(params);
        invalidate();
    }

    public void initPagicCoords(){
        mRect.setPagicLeft(getX());
        mRect.setPagicTop(getY());
        mRect.setPagicRight(getX()+ getWidth());
        mRect.setPagicBottom(getY()+ getHeight());
    }
    private void initPaint() {
        /**
        * 方法描述  初始化画笔
        * @param [] 参数描述
        * @return void 返回值描述
        */
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStrokeWidth(5);
        mLinePaint.setStrokeJoin(Paint.Join.ROUND);
        mLinePaint.setStrokeCap(Paint.Cap.ROUND);
        mLinePaint.setStyle(Paint.Style.STROKE);
    }

    public void reInitPaint(int color,int strokeWidth) {
        /**
         * 方法描述  初始化画笔
         * @param [] 参数描述
         * @return void 返回值描述
         */
        mLinePaint.setColor(color);
        mLinePaint.setStrokeWidth(strokeWidth);
    }

    public void startDrawing(){
        /**
        * 方法描述  调用此方法开始绘图
        * @param [] 参数描述
        * @return void 返回值描述
        */
        mCurrentState = mState.DRAW;
    }

    public void restoreImg(Bitmap bm){
        /**
        * 方法描述  恢复操作之前的图片
        * @param [] 参数描述
        * @return void 返回值描述
        */
        mRect.setmBase(bm);
        mBase = bm;
        reInit();
    }

    public void startCropping(FrameLayout mBaseLayout){
        /**
         * 方法描述  调用此方法开始截图，传递所在Layout来添加cutView
         * @param [] 参数描述   所在Layout
         * @return void 返回值描述
         */
        mCurrentState = mState.CROP;
        addCropView(mBaseLayout);
    }
    private void addCropView(FrameLayout mBaseLayout){
        /**
         * 方法描述  在pagicView正上方添加裁剪框
         * @param [mBaseLayout] 参数描述 所在layout
         * @return void 返回值描述
         */
        cutView cutView = new cutView(super.getContext());
        //设置ID
        cutView.setId(R.id.cutView);
        //设置各项layout参数
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        mBaseLayout.addView(cutView,params);
    }

    public void startRotating(){
        /**
         * 方法描述  调用此方法开始截图，传递所在Layout来添加cutView
         * @param [] 参数描述   所在Layout
         * @return void 返回值描述
         */
        mCurrentState = mState.ROTATE;
    }

    public void startTexting(FrameLayout mBaseLayout) {
        /**
        * 方法描述  开始添加文字
        * @param [mBaseLayout] 参数描述
        * @return void 返回值描述
        */
        mCurrentState = mState.TEXT;
        addTextView(mBaseLayout);
    }

    private void addTextView(FrameLayout mBaseLayout){
        /**
         * 方法描述  在pagicView正上方添加裁剪框
         * @param [mBaseLayout] 参数描述 所在layout
         * @return void 返回值描述
         */
        textView textView = new textView(super.getContext());
        //设置ID
        textView.setId(R.id.textView);
        //设置各项layout参数
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        mBaseLayout.addView(textView,params);
    }

    public void initState(){
        /**
        * 方法描述  将状态恢复到无操作
        * @param [] 参数描述
        * @return void 返回值描述
        */
        mCurrentState = mState.START;
    }



    public Bitmap scaleBitmap(Bitmap img) {
        /**
         * 方法描述  调用此方法使得图片适应屏幕大小
         * @param [bm] 参数描述
         * @return android.graphics.Bitmap 返回值描述
         */
        if (img == null) {
            return img;
        }
        //用图片的长除以容器宽度，宽除以容器高度，取大的值作为改变系数
        mPreScale = Math.max((float) img.getWidth() / (float) mLayoutWidth,(float) img.getHeight() / (float) (mLayoutHeight * 0.8));
        //使用改变系数将图片的长或宽适应容器并按比例改变另一边
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(img, (int) (img.getWidth() / mPreScale),
                (int) (img.getHeight() / mPreScale), false);
        return scaledBitmap;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        /**
        * 方法描述  view全都初始化完后确定坐标
        * @param [hasFocus] 参数描述
        * @return void 返回值描述
        */
        initPagicCoords();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    /**
    * 方法描述 当控件高和宽设置为WRAP_CONTENT时设置大小
    * @param [widthMeasureSpec, heightMeasureSpec] 参数描述
    * @return void 返回值描述
    */
        int mWidth = mRect.getmBase().getWidth();
        int mHeight = mRect.getmBase().getHeight();

        if (getLayoutParams().width == ViewGroup.LayoutParams.WRAP_CONTENT && getLayoutParams().height == ViewGroup.LayoutParams.WRAP_CONTENT) {
            setMeasuredDimension(mWidth, mHeight);
        }

        setMeasuredDimension(mWidth, mHeight);
}

    @Override
    protected void onDraw(Canvas canvas) {
//      super.onDraw(canvas);
        canvas.drawBitmap(mRect.getmBase(),mMatrix,null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**
        * 方法描述 当前状态为DRAW时触发touch事件进行画图
        * @param [event] 参数描述
        * @return boolean 返回值描述
        */
        boolean ret = super.onTouchEvent(event);

        if ( mCurrentState != mState.DRAW ) {
            float eventX = event.getX();
            float eventY = event.getY();
            return ret;
        }
        //获取按下的坐标
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ret = true;
                mLastX = eventX;
                mLastY = eventY;
                break;
            case MotionEvent.ACTION_MOVE:
                ret = true;
                //在起点和终点连线并不断刷新按下位置
                mDrawLineCanvas.drawLine(mLastX, mLastY, eventX, eventY, mLinePaint);
                mLastX = eventX;
                mLastY = eventY;
                this.postInvalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                ret = false;
                break;
        }
        return ret;
    }

}

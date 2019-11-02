package ustc.sse.meitu.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.AppCompatEditText;

import ustc.sse.meitu.pojo.picData;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

public class textView extends AppCompatEditText {
    float downX;    //按下的X坐标
    float downY;    //按下的Y坐标
    boolean isTopCorner;   //是否按在上角落
    boolean isBottomCorner;   //是否按在下角落
    boolean isMove; //是否想要移动框
    boolean isConfirmed = false;//判断是否确认还是继续调整

    float rectLeft ;    //框左上X
    float rectRight ;   //框右下X
    float rectTop ;     //框左上Y
    float rectBottom ;  //框右下Y
    float scale;        //放缩规模
    private int maxWidth;  //边界宽度
    private int maxHeight; //边界高度
    private Paint rectpaint;    //框画笔
    private Paint cornerpaint;  //角落画笔
    static picData mRect;   //View间的共享数据

    public textView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public textView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public textView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        /**
        * 方法描述  各种初始化
        * @param [context] 参数描述
        * @return void 返回值描述
        */
        mRect = (picData) context.getApplicationContext();
        maxHeight = (int)mRect.getPagicBottom();
        maxWidth = (int)mRect.getPagicRight();
        rectpaint = new Paint();
        rectpaint.setAntiAlias(true);
        rectpaint.setColor(Color.GRAY);
        rectpaint.setStyle(Paint.Style.FILL);
        rectpaint.setStrokeWidth(3);
        cornerpaint = new Paint();
        cornerpaint.setAntiAlias(true);
        cornerpaint.setColor(Color.BLACK);
        cornerpaint.setStyle(Paint.Style.FILL);
        cornerpaint.setStrokeWidth(3);
        this.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        this.setText("在这里输入");
        this.setSelection(this.getText().length());
        this.setBackground(null);
        //将内容保存
        this.setDrawingCacheEnabled(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //当确认后绘制时不需要文字框
        if(!isConfirmed) {
            setTextCoords();
            int h = (int) ((rectBottom - rectTop) / 6);
            canvas.drawCircle(getWidth(), getHeight(), h, cornerpaint);
            canvas.drawCircle(0, 0, h, cornerpaint);
            canvas.drawLine(0, 0, getWidth(), 0, rectpaint);
            canvas.drawLine(0, 0, 0, getHeight(), rectpaint);
            canvas.drawLine(getWidth(), 0, getWidth(), getHeight(), rectpaint);
            canvas.drawLine(0, getHeight(), getWidth(), getHeight(), rectpaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter){
    /**
    * 方法描述  当文字改变后自适应改变文字框大小和位置
    * @param [text, start, lengthBefore, lengthAfter] 参数描述
    * @return void 返回值描述
    */
        ViewGroup.LayoutParams textParam = this.getLayoutParams();
        if(textParam==null){
            return;
        }
        textParam.height=ViewGroup.LayoutParams.WRAP_CONTENT;
        textParam.width =ViewGroup.LayoutParams.WRAP_CONTENT;
        this.setLayoutParams(textParam);
        setTextCoords();
    }
//    @Override
//    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
//        super.onFocusChanged(focused, direction, previouslyFocusedRect);
//        if (focused) {
//            return;
//        } else {
//            if(TextUtils.isEmpty(this.getText())){
//                this.setText("在这里输入");
//                this.setSelection(this.getText().length());
//            }
//        }
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int w = (int) ((rectRight - rectLeft)/4);
        int h = (int) ((rectBottom - rectTop)/4);
        float textX = this.getX();  //左上绝对坐标X
        float textY = this.getY();  //左上绝对坐标Y

        switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //用getRawX获取在屏幕中的绝对坐标
                    downX = event.getRawX();
                    downY = event.getRawY();
                    //判断是否想输入文字
                    if((downX >= rectLeft+w && downX <= rectRight-w)){
                        super.onTouchEvent(event);
                        InputMethodManager imm = (InputMethodManager) this.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        return true;
                    }
                    //判断是否按在两个角落
                    if ((downX >= rectLeft && downX <= rectLeft+w)) {
                        isTopCorner = true;
//                        Log.v("touch","topcorner");
                    }
                    else if ((downX <= rectRight && downX >= rectRight - w)) {
                        isBottomCorner = true;
//                        Log.v("touch","bottomcorner");
//                        Log.v("downX",Float.toString(downX));
//                        Log.v("downY",Float.toString(downY));
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    float moveX = event.getRawX();
                    float moveY = event.getRawY();
                    //得到手指移动距离
                    float slideX= moveX - downX ;
                    float slideY= moveY - downY;
                    if (isTopCorner) {
                        textX += slideX;
                        textY += slideY;
                        //限制文字框在图像中
                        if(textX<mRect.getPagicLeft()){
                            textX = mRect.getPagicLeft();
                        }
                        if(textY<mRect.getPagicTop()){
                            textY = mRect.getPagicTop();
                        }
                        if(textX+getWidth()>maxWidth){
                            textX = maxWidth-getWidth();
                        }
                        if(textY+getHeight()>maxHeight){
                            textY = maxHeight-getHeight();
                        }
                        //设置绝对坐标
                        this.setX(textX);
                        this.setY(textY);
                    }

                    else if(isBottomCorner){
//                        float width = getWidth();
                        //获取文字大小
                        float height = getTextSize();
//                        Log.v("width", Float.toString(width));
//                        Log.v("height", Float.toString(height));
                        //等比例放缩文字
                        scale = (height + slideY) / height;
                        this.setTextSize(COMPLEX_UNIT_PX, height*scale);
                        //根据文字大小设置文字框适应
                        ViewGroup.LayoutParams textParam = this.getLayoutParams();
                        textParam.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        textParam.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                        this.setLayoutParams(textParam);
                    }

                    downX = moveX;
                    downY = moveY;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    isMove = false;
                    isTopCorner = false;
                    isBottomCorner = false;
                    setTextCoords();
                    break;
            }

        return true;
    }

    private void setTextCoords(){
        /**
        * 方法描述  设置文字框当前四个角落坐标
        * @param [] 参数描述
        * @return void 返回值描述
        */
        rectLeft = this.getX();
        rectTop = this.getY();
        rectRight = rectLeft + this.getWidth();
        rectBottom = rectTop + this.getHeight();
    }

    public void drawText(int pagicLeft,int pagicTop){
        /**
        * 方法描述  调用此方法在底图中绘制文字
        * @param [pagicLeft, pagicTop] 参数描述
        * @return void 返回值描述
        */
//        draw(new Canvas(mRect.getmBase()));
        isConfirmed = true;
        invalidate();
        Canvas canvas = new Canvas(mRect.getmBase());
        //获取控件中保存的内容
        Bitmap bm = this.getDrawingCache();
        canvas.drawBitmap(bm, this.getX() - pagicLeft, this.getY() - pagicTop, null);
    }
}

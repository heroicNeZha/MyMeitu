package ustc.sse.meitu.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import ustc.sse.meitu.R;

import ustc.sse.meitu.widget.cutView;
import ustc.sse.meitu.widget.pagicView;


public class drawFragment extends Fragment {
    private ImageView btnDraw, btnConfirm, btnCancel,btnRed,
            btnBlue, btnGreen, btnIndigo, btnOrange,
            btnPurple,btnYellow, btnBlack, btnBrush1,
            btnBrush2, btnBrush3, chosen_color, chosen_width;
    private pagicView pagicView;
    private Bitmap tempImg;
    private int color = Color.BLACK;
    private int strokeWidth = 5;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_draw, container, false);
        initViews(view);
        chosen_color.setBackgroundResource(R.drawable.color_circle_black);
        chosen_width.setBackgroundResource(R.drawable.brush_size1);
        pagicView.reInitPaint(Color.BLACK,strokeWidth);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  取消，获取并替换底部菜单fragment，并恢复为原图
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                FragmentManager fm=getFragmentManager();
                FragmentTransaction tx=fm.beginTransaction();
                tx.replace(R.id.bottomContainer,new menuFragment());
                tx.commit();
                pagicView.restoreImg(tempImg);
                pagicView.initState();

            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  确定后替换底部菜单fragment，自动保存绘图
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                FragmentManager fm=getFragmentManager();
                FragmentTransaction tx=fm.beginTransaction();
                tx.replace(R.id.bottomContainer,new menuFragment());
                pagicView.initState();
                tx.commit();
//                frameLayout.invalidate();
            }
        });
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  更换画笔颜色
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                color = Color.RED;
                pagicView.reInitPaint(Color.RED,strokeWidth);
                chosen_color.setBackgroundResource(R.drawable.color_circle_red);
            }
        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  更换画笔颜色
                 * @param [view] 参数描述
                        * @return void 返回值描述
                 */
                color = Color.BLUE;
                pagicView.reInitPaint(Color.BLUE,strokeWidth);
                chosen_color.setBackgroundResource(R.drawable.color_circle_blue);
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  更换画笔颜色
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                color = Color.GREEN;
                pagicView.reInitPaint(Color.GREEN,strokeWidth);
                chosen_color.setBackgroundResource(R.drawable.color_circle_green);
            }
        });
        btnIndigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  更换画笔颜色
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                color = 0xFF00FFFF;
                pagicView.reInitPaint(0xFF00FFFF,strokeWidth);
                chosen_color.setBackgroundResource(R.drawable.color_circle_indigo);
            }
        });
        btnOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  更换画笔颜色
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                color = 0xFFFF7F00;
                pagicView.reInitPaint(0xFFFF7F00,strokeWidth);
                chosen_color.setBackgroundResource(R.drawable.color_circle_orange);
            }
        });
        btnPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  更换画笔颜色
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                color = 0xFFBB00FF;
                pagicView.reInitPaint(0xFFBB00FF,strokeWidth);
                chosen_color.setBackgroundResource(R.drawable.color_circle_purple);
            }
        });
        btnYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  更换画笔颜色
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                color = Color.YELLOW;
                pagicView.reInitPaint(color,strokeWidth);
                chosen_color.setBackgroundResource(R.drawable.color_circle_yellow);
            }
        });
        btnBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  更换画笔颜色
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                color = Color.BLACK;
                pagicView.reInitPaint(color,strokeWidth);
                chosen_color.setBackgroundResource(R.drawable.color_circle_black);
            }
        });
        btnBrush1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  更换画笔粗细
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                strokeWidth = 5;
                pagicView.reInitPaint(color,strokeWidth);
                chosen_width.setBackgroundResource(R.drawable.brush_size1);
            }
        });
        btnBrush2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  更换画笔粗细
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                strokeWidth = 10;
                pagicView.reInitPaint(color,strokeWidth);
                chosen_width.setBackgroundResource(R.drawable.brush_size2);
            }
        });
        btnBrush3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  更换画笔粗细
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                strokeWidth = 15;
                pagicView.reInitPaint(color,strokeWidth);
                chosen_width.setBackgroundResource(R.drawable.brush_size3);
            }
        });
        return view;
    }
    private void initViews( View view) {
        /**
         * 方法描述 初始化绑定各个实例按钮，同时新建一个BITMAP用来恢复
         * @param [] 参数描述
         * @return void 返回值描述
         */
        btnDraw = (ImageView)view.findViewById(R.id.mark_button);
        btnCancel = (ImageView)view.findViewById(R.id.cancel_button);
        btnConfirm = (ImageView)view.findViewById(R.id.confirm_button);
        btnRed = (ImageView)view.findViewById(R.id.red_button);
        btnBlack = (ImageView)view.findViewById(R.id.black_button);
        btnYellow = (ImageView)view.findViewById(R.id.yellow_button);
        btnPurple = (ImageView)view.findViewById(R.id.purple_button);
        btnOrange = (ImageView)view.findViewById(R.id.orange_button);
        btnIndigo = (ImageView)view.findViewById(R.id.indigo_button);
        btnGreen = (ImageView)view.findViewById(R.id.green_button);
        btnBlue = (ImageView)view.findViewById(R.id.blue_button);
        btnBrush1 = (ImageView)view.findViewById(R.id.brush1);
        btnBrush2 = (ImageView)view.findViewById(R.id.brush2);
        btnBrush3 = (ImageView)view.findViewById(R.id.brush3);
        chosen_color = (ImageView)view.findViewById(R.id.chosen_color);
        chosen_width = (ImageView)view.findViewById(R.id.chosen_width);
        pagicView = (pagicView)getActivity().findViewById(R.id.pagicView);
        tempImg = Bitmap.createBitmap(pagicView.mBase);
    }
}

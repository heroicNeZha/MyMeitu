package ustc.sse.meitu.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import ustc.sse.meitu.R;

import ustc.sse.meitu.widget.pagicView;

public class rotateFragment extends Fragment {
    private ImageView btnCancel,btnConfirm,btnRotateCW,btnRotateACW,btnFlip; //多个按钮
    private FrameLayout frameLayout;
    private pagicView pagicView;
    private Bitmap tempImg;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rotate, container, false);
        initViews(view);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  取消旋转，获取并替换底部菜单fragment，
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
                 * 方法描述  获取并替换底部菜单fragment，刷新主视图
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
        btnRotateCW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  调用旋转，刷新
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                pagicView.mRect.setmBase( rotateBitmap(pagicView.mBase,90));
                pagicView.reInit();
            }
        });
        btnRotateACW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  调用旋转，刷新
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                pagicView.mRect.setmBase( rotateBitmap(pagicView.mBase,-90));
                pagicView.reInit();
            }
        });
        btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  调用翻转，刷新
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                pagicView.mRect.setmBase( flipBitmap(pagicView.mBase));
                pagicView.reInit();
            }
        });
        return view;
    }

    private Bitmap rotateBitmap(Bitmap mBitmap, float angle) {
        /**
        * 方法描述  旋转BITMAP，角度为正是顺时针
        * @param [mBitmap, angle] 参数描述
        * @return android.graphics.Bitmap 返回值描述
        */
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(mBitmap,0,0,mBitmap.getWidth(),mBitmap.getHeight(),matrix,true);
    }

    private Bitmap flipBitmap(Bitmap mBitmap) {
        /**
         * 方法描述  旋转BITMAP，角度为正是顺时针
         * @param [mBitmap, angle] 参数描述
         * @return android.graphics.Bitmap 返回值描述
         */
        Matrix matrix = new Matrix();
        matrix.postScale(-1, 1);
        return Bitmap.createBitmap(mBitmap,0,0,mBitmap.getWidth(),mBitmap.getHeight(),matrix,true);
    }


    private void initViews( View view) {
        /**
         * 方法描述 初始化绑定各个按钮
         * @param [] 参数描述
         * @return void 返回值描述
         */
        btnCancel = (ImageView)view.findViewById(R.id.cancel_button);
        btnConfirm = (ImageView)view.findViewById(R.id.confirm_button);
        btnRotateCW = (ImageView) view.findViewById(R.id.rotate_clockwise_button);
        btnRotateACW = (ImageView) view.findViewById(R.id.rotate_anticlockwise_button);
        btnFlip = (ImageView) view.findViewById(R.id.filp_button);
        pagicView = (pagicView)getActivity().findViewById(R.id.pagicView);
        frameLayout = (FrameLayout)getActivity().findViewById(R.id.mBaseView);
        tempImg = Bitmap.createBitmap(pagicView.mBase);
    }
}

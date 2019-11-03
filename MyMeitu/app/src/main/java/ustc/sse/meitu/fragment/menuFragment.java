package ustc.sse.meitu.fragment;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;


import ustc.sse.meitu.R;

import ustc.sse.meitu.widget.pagicView;

public class menuFragment extends Fragment {
    private ImageView btnCrop,btnReset,btnRotate,btnGallery,btnDraw,btnText,btnSave;    //各个按钮的引用
    private ustc.sse.meitu.widget.pagicView pagicView; //主视图的引用

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        /**
        * 方法描述  绑定菜单中各个按钮事件触发
        * @param [inflater, container, savedInstanceState] 参数描述
        * @return android.view.View 返回值描述
        */
        View view=inflater.inflate(R.layout.fragment_menu,container,false);
        initViews(view);
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                * 方法描述  调用绘图方法开始绘图
                * @param [view] 参数描述
                * @return void 返回值描述
                */
                FragmentManager fm=getFragmentManager();
                FragmentTransaction tx=fm.beginTransaction();
                tx.replace(R.id.bottomContainer,new drawFragment());
                pagicView.startDrawing();
                tx.commit();
            }
        });
        btnCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                * 方法描述  替换底部工具栏fragment，传递Layout对象用于添加裁剪框开始裁剪
                * @param [view] 参数描述
                * @return void 返回值描述
                */
                FragmentManager fm=getFragmentManager();
                FragmentTransaction tx=fm.beginTransaction();
                tx.replace(R.id.bottomContainer,new cropFragment());
                pagicView.startCropping((FrameLayout) getActivity().findViewById(R.id.mBaseView));
                tx.commit();

            }
        });
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  替换底部工具栏fragment，开始旋转
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                FragmentManager fm=getFragmentManager();
                FragmentTransaction tx=fm.beginTransaction();
                tx.replace(R.id.bottomContainer,new rotateFragment());
                pagicView.startRotating();
                tx.commit();

            }
        });

        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  替换底部工具栏fragment，开始添加文字
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                pagicView.initPagicCoords();
                FragmentManager fm=getFragmentManager();
                FragmentTransaction tx=fm.beginTransaction();
                tx.replace(R.id.bottomContainer,new textFragment());
                tx.commit();
                pagicView.startTexting((FrameLayout) getActivity().findViewById(R.id.mBaseView));

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  保存
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
//                Intent intent = new Intent();
//                intent.setClass(getActivity(), TestActivity.class);//this前面为当前activty名称，class前面为要跳转到得activity名称
//                startActivity(intent);
                getActivity().finish();

            }
        });
        return view;

    }
    private void initViews( View view) {
        /**
         * 方法描述 初始化绑定各个按钮和fragment,其中在fragment中的使用view寻找，在Activity中的使用getActivity寻找
         * @param [] 参数描述
         * @return void 返回值描述
         */
        btnCrop = (ImageView)view.findViewById(R.id.crop_button);
        btnRotate = (ImageView)view.findViewById(R.id.rotate_button);
        btnDraw = (ImageView)view.findViewById(R.id.mark_button);
        btnText = (ImageView)view.findViewById(R.id.text_button);
        btnSave = (ImageView)view.findViewById(R.id.save_button);
        pagicView = (pagicView)getActivity().findViewById(R.id.pagicView);
    }
}
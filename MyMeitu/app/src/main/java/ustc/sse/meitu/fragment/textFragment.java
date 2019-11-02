package ustc.sse.meitu.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import ustc.sse.meitu.R;

import ustc.sse.meitu.widget.pagicView;
import ustc.sse.meitu.widget.textView;

public class textFragment extends Fragment {
    private ImageView btnCancel,btnConfirm; //两个按钮
    private textView textView;    //文字框视图
    private pagicView pagicView;    //主要任务视图
    private FrameLayout frameLayout;    //pagicView所在的Layout，用来添加删除textView
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        /**
         * 方法描述  进入文字模式后绑定两个按钮
         * @param [inflater, container, savedInstanceState] 参数描述
         * @return android.view.View 返回值描述
         */
        View view = inflater.inflate(R.layout.fragment_text, container, false);
        initViews(view);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  获取并替换底部菜单fragment，并移除文字框
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                FragmentManager fm=getFragmentManager();
                FragmentTransaction tx=fm.beginTransaction();
                tx.replace(R.id.bottomContainer,new menuFragment());
                tx.commit();
                frameLayout.removeView(textView);
                pagicView.initState();

            }
        });
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 方法描述  确定后调用添加文字方法，获取并替换底部菜单fragment，并移除文字框view，刷新主视图
                 * @param [view] 参数描述
                 * @return void 返回值描述
                 */
                textView.drawText(pagicView.getLeft(),pagicView.getTop());
                frameLayout.removeView(textView);
                FragmentManager fm=getFragmentManager();
                FragmentTransaction tx=fm.beginTransaction();
                tx.replace(R.id.bottomContainer,new menuFragment());
                pagicView.reInit();
                pagicView.initState();
                tx.commit();

//                frameLayout.invalidate();
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
        btnCancel = (ImageView)view.findViewById(R.id.cancel_button);
        btnConfirm = (ImageView)view.findViewById(R.id.confirm_button);
        pagicView = (pagicView)getActivity().findViewById(R.id.pagicView);
        textView = (textView) getActivity().findViewById(R.id.textView);
        frameLayout = (FrameLayout)getActivity().findViewById(R.id.mBaseView);
    }
}

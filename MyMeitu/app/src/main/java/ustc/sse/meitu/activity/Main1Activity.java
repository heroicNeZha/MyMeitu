package ustc.sse.meitu.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ustc.sse.meitu.fragment.menuFragment;
import ustc.sse.meitu.R;
import ustc.sse.meitu.widget.pagicView;

public class Main1Activity extends Activity {
//    private ImageView btnCrop,btnReset,btnRotate,btnGallery,btnDraw,btnText,btnSave;
//    private TextView guideView;
    //用来调整主页面上下两部分大小
    private pagicView pagicView;
    private FrameLayout pagic;
    private LinearLayout menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        initViews();
    }
    private void initViews() {
        /**
        * 方法描述 初始化绑定各个按钮和fragment
        * @param [] 参数描述
        * @return void 返回值描述
        */
        pagic = (FrameLayout)findViewById(R.id.pagic) ;
        menu = (LinearLayout) findViewById(R.id.menu) ;
        pagicView = (pagicView)findViewById(R.id.pagicView);

        //获取两个部分的layout参数并设置高度，图片部分为屏幕的上0.8，菜单部分为屏幕的下0.2
        ViewGroup.LayoutParams pagicParam = pagic.getLayoutParams();
        pagicParam.height=(int)(pagicView.mLayoutHeight*0.8);
        pagic.setLayoutParams(pagicParam);
        ViewGroup.LayoutParams menuParam = menu.getLayoutParams();
        menuParam.height=(int)(pagicView.mLayoutHeight*0.2);
        menu.setLayoutParams(menuParam);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        tx.replace(R.id.bottomContainer, new menuFragment());
        tx.commit();

//        btnCrop = (ImageView) findViewById(R.id.crop_button);
//        btnRotate = (ImageView)findViewById(R.id.rotate_button);
//        btnDraw = (ImageView)findViewById(R.id.mark_button);
//        btnGallery = (ImageView)findViewById(R.id.gallery_button);
//        btnText = (ImageView)findViewById(R.id.text_button);
//        btnSave = (ImageView)findViewById(R.id.save_button);
//        guideView = (TextView)findViewById(R.id.guideView);
//        mBaseView = (FrameLayout) findViewById(R.id.mBaseView);
    }
}

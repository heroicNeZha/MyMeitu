package ustc.sse.meitu.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ustc.sse.meitu.R;
import ustc.sse.meitu.Service.ImageService;
import ustc.sse.meitu.adapter.LocalImageAdapter;
import ustc.sse.meitu.adapter.OnlineImageAdapter;
import ustc.sse.meitu.listener.onItemClickListener;
import ustc.sse.meitu.pojo.Image;
import ustc.sse.meitu.utils.ToastUtils;

public class OnlineActivity extends AppCompatActivity implements onItemClickListener {

    ArrayList<Image> images;

    OnlineImageAdapter imageAdapter;

    @BindView(R.id.nav_view)
    BottomNavigationView navView;
    @BindView(R.id.ll_delete)
    ConstraintLayout llDelete;
    @BindView(R.id.rv_local)
    RecyclerView rvLocal;
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.iv_cancle)
    ImageView ivCancle;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.container)
    ConstraintLayout container;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_dashboard:
                return true;
            case R.id.navigation_notifications:
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
        ButterKnife.bind(this);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        navView.getMenu().getItem(1).setChecked(true);

        initRecycleView();
    }

    private void initRecycleView() {
        images = new ArrayList<>();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvLocal.setLayoutManager(layoutManager);//布局管理器
        imageAdapter = new OnlineImageAdapter(this);
        imageAdapter.setListener(this);
        imageAdapter.initImage();
        rvLocal.setAdapter(imageAdapter);
        rvLocal.setItemAnimator(new DefaultItemAnimator());
    }

    //监听器
    @Override
    public void onItemClick(Image image) {

    }

    @Override
    public void onItemLongClick(Image image) {
        if (!llDelete.isShown()) {
            llDelete.setVisibility(View.VISIBLE);
            navView.setVisibility(View.GONE);
            ArrayList<Image> deleteArrayList = new ArrayList<>();
            deleteArrayList.add(image);
            imageAdapter.setDeleteArrayList(deleteArrayList);
            imageAdapter.setInDeletionMode(true);
        }
    }

    @OnClick({R.id.iv_delete, R.id.tv_delete})
    public void onDeleteClick(View view) {
        imageAdapter.deleteSelected();
        llDelete.setVisibility(View.GONE);
        navView.setVisibility(View.VISIBLE);
        imageAdapter.setInDeletionMode(false);
    }

    @OnClick({R.id.iv_cancle, R.id.tv_cancle})
    public void onCancleClick(View view) {
        llDelete.setVisibility(View.GONE);
        navView.setVisibility(View.VISIBLE);
        ArrayList<Image> deleteArrayList = new ArrayList<>();
        imageAdapter.setDeleteArrayList(deleteArrayList);
        imageAdapter.setInDeletionMode(false);
    }

    @OnClick({R.id.iv_upload, R.id.tv_upload})
    public void onDownloadClick(View view) {
        if (imageAdapter.downloadSelected())
            ToastUtils.showLong(this, "下载成功！");
        else
            ToastUtils.showLong(this, "下载失败！");
        llDelete.setVisibility(View.GONE);
        navView.setVisibility(View.VISIBLE);
        imageAdapter.setInDeletionMode(false);
    }
}

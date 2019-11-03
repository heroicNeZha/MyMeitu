package ustc.sse.meitu.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
import ustc.sse.meitu.adapter.LocalImageAdapter;
import ustc.sse.meitu.pojo.Image;

public class MainActivity extends AppCompatActivity implements LocalImageAdapter.onItemClickListener {

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    ArrayList<Image> images;

    LocalImageAdapter imageAdapter;


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

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_dashboard:
                intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
                return true;
            case R.id.navigation_notifications:
                intent = new Intent(MainActivity.this, MeActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //登录
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        showInfoDialog();

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        if (verifyPermissions(MainActivity.this, PERMISSIONS_STORAGE[0])||verifyPermissions(MainActivity.this, PERMISSIONS_STORAGE[1])) {
            ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, 3);
        }

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initRecycleView();
    }

    //检查权限
    public boolean verifyPermissions(Activity activity, String permission) {
        int Permission = ActivityCompat.checkSelfPermission(activity, permission);
        if (Permission == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        initImage(images);
        imageAdapter.replaceAll(images);
    }

    private void initRecycleView() {
        images = new ArrayList<>();
        initImage(images);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvLocal.setLayoutManager(layoutManager);//布局管理器
        imageAdapter = new LocalImageAdapter();
        imageAdapter.setListener(this);
        imageAdapter.replaceAll(images);
        rvLocal.setAdapter(imageAdapter);
        rvLocal.setItemAnimator(new DefaultItemAnimator());
    }

    private void initImage(List<Image> images) {
        images.clear();
        File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/data/ustc.meitu/");
        File[] files = dir.listFiles();
        for (File f : files) {
            Image image = new Image(f.getPath(), "图片" + f.getName().substring(0,13)+"说点什么吧..");
            try {
                FileInputStream fis = new FileInputStream(image.getPath());
                Bitmap bitmap = BitmapFactory.decodeStream(fis);
                image.setBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            images.add(image);
        }
    }

    private void showInfoDialog() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(MainActivity.this);
        normalDialog.setTitle("组员信息");
        normalDialog.setMessage("学号SA19225344 姓名童旭\n学号SA19225231 姓名李泽伟\n学号SA19225241 姓名廖文\n学号SA19225180 姓名胡伟康");
        normalDialog.setPositiveButton("确定",
                (dialog, which) -> {
                });
        normalDialog.setNegativeButton("关闭",
                (dialog, which) -> {
                });
        normalDialog.show();
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

    @OnClick({ R.id.iv_cancle, R.id.tv_cancle})
    public void onCancleClick(View view) {
            llDelete.setVisibility(View.GONE);
            navView.setVisibility(View.VISIBLE);
            ArrayList<Image> deleteArrayList = new ArrayList<>();
            imageAdapter.setDeleteArrayList(deleteArrayList);
            imageAdapter.setInDeletionMode(false);
    }
}

package ustc.sse.meitu.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import ustc.sse.meitu.utils.DividerItemDecoration;
import ustc.sse.meitu.R;
import ustc.sse.meitu.adapter.ImageAdapter;
import ustc.sse.meitu.pojo.Image;

public class MeActivity extends AppCompatActivity {

    private List<Image> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        //头像
        ImageView avator = (ImageView) findViewById(R.id.imageView);
        avator.setImageResource(R.drawable.logo);
        //江苏苏州
        TextView tv = (TextView) findViewById(R.id.textView3);
        tv.setBackgroundColor(Color.argb(140, 245, 245, 245)); //背景透明度
        //toolbar
        Toolbar mToolbarTb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbarTb);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //recyclerView
        initImage();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);//布局管理器
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        ImageAdapter imageAdapter = new ImageAdapter(images);
        recyclerView.setAdapter(imageAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initImage() {
        File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/data/ustc.meitu/");
        File[] files = dir.listFiles();
        for (File f : files) {
            Image image = new Image(f.getPath(), "图片id" + f.getName());
            images.add(image);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
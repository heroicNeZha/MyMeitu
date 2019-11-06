package ustc.sse.meitu.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ustc.sse.meitu.R;
import ustc.sse.meitu.adapter.ImageAdapter;
import ustc.sse.meitu.pojo.Image;
import ustc.sse.meitu.utils.DividerItemDecoration;

public class MeActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView avator;
    @BindView(R.id.textView3)
    TextView tvAddress;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.textView11)
    TextView tvImageCount;

    private List<Image> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);
        //头像
        avator.setImageResource(R.drawable.avator);
        //江苏苏州
        tvAddress.setBackgroundColor(Color.argb(140, 245, 245, 245)); //背景透明度
        //toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //recyclerView
        initImage();
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
        tvImageCount.setText(files.length + " 图片");
        for (File f : files) {
            Image image = new Image(f.getPath(), "图片" + f.getName().substring(0, 13) + "说点什么吧..");
            try {
                FileInputStream fis = new FileInputStream(image.getPath());
                Bitmap bitmap = BitmapFactory.decodeStream(fis);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, true);
                image.setBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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

package ustc.sse.meitu.activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.provider.ContactsContract;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ustc.sse.meitu.R;
import ustc.sse.meitu.adapter.ImageAdapter;
import ustc.sse.meitu.adapter.LocalImageAdapter;
import ustc.sse.meitu.pojo.Image;
import ustc.sse.meitu.utils.DividerItemDecoration;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_dashboard:
                    return true;
                case R.id.navigation_notifications:
                    Intent intent = new Intent(MainActivity.this, MeActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //登录
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        initRecycleView();
    }

    private void initRecycleView(){
        ArrayList<Image> images = new ArrayList<>();
        initImage(images);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_local);
        StaggeredGridLayoutManager layoutManager =new StaggeredGridLayoutManager(2,  StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);//布局管理器
        LocalImageAdapter imageAdapter = new LocalImageAdapter();
        imageAdapter.replaceAll(images);
        recyclerView.setAdapter(imageAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void initImage(List<Image> images) {
        try {
            for (int i = 1; i <= 10; i++) {

                Field field = R.drawable.class.getDeclaredField("img" + i);
                field.setAccessible(true);
                Image image = new Image(field.getInt(field.getName()), "第" + i + "张图片");
                images.add(image);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}

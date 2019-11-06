package ustc.sse.meitu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ustc.sse.meitu.R;
import ustc.sse.meitu.listener.onItemClickListener;
import ustc.sse.meitu.pojo.Image;

public class OnlineActivity extends AppCompatActivity implements onItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online);
    }

    @Override
    public void onItemClick(Image image) {

    }

    @Override
    public void onItemLongClick(Image image) {

    }
}

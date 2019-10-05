package ustc.sse.meitu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        ImageView avator = (ImageView) findViewById(R.id.imageView);
        avator.setImageResource(R.drawable.logo);

        TextView tv = (TextView) findViewById(R.id.textView3);
        tv.setBackgroundColor(Color.argb(140, 245, 245, 245)); //背景透明度
    }
}

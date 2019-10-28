package ustc.sse.meitu.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ustc.sse.meitu.R;

public class ImageActivity extends AppCompatActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);

        ivImage.setImageResource(getIntent().getIntExtra("image", 0));

    }

    @OnClick(R.id.iv_image)
    public void onClick() {
        ActivityCompat.finishAfterTransition(ImageActivity.this);
    }
}

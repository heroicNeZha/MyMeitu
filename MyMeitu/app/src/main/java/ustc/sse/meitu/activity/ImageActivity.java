package ustc.sse.meitu.activity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import butterknife.OnTouch;
import ustc.sse.meitu.R;
import ustc.sse.meitu.utils.FileUtils;
import ustc.sse.meitu.utils.ToastUtils;

public class ImageActivity extends AppCompatActivity {

    @BindView(R.id.iv_image)
    ImageView ivImage;

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        try {
            path = getIntent().getStringExtra("image");
            FileInputStream fis = new FileInputStream(path);
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            ivImage.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.iv_image)
    public void onClick() {
        ActivityCompat.finishAfterTransition(ImageActivity.this);
    }

    @OnLongClick(R.id.iv_image)
    public void onLongClick() {
        showNormalDialog();
    }

    private void showNormalDialog() {
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(ImageActivity.this);
        normalDialog.setTitle("删除");
        normalDialog.setMessage("是否删除图片？");
        normalDialog.setPositiveButton("确定",
                (dialog, which) -> {
                    FileUtils.deleteFile(path);
                    ActivityCompat.finishAfterTransition(ImageActivity.this);
                });
        normalDialog.setNegativeButton("关闭",
                (dialog, which) -> {
                });
        normalDialog.show();
    }
}

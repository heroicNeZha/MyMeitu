package ustc.sse.meitu.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ustc.sse.meitu.R;
import ustc.sse.meitu.pojo.MyApplicationContext;
import ustc.sse.meitu.utils.ScreenUtils;
import ustc.sse.meitu.pojo.PicData;

public class AddActivity extends AppCompatActivity {
    public static PicData mRect;

    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.CAMERA
    };


    @BindView(R.id.btn_camera)
    Button cameraButton;
    @BindView(R.id.btn_album)
    Button albumButton;
    @BindView(R.id.iv_image)
    ImageView imageView;
    @BindView(R.id.tv_state)
    TextView stateText;


    private static final int ALBUM_REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST_CODE = 2;
    private static final int CROP_REQUEST_CODE = 3;

    private File tempFile;

    @BindView(R.id.nav_view)
    BottomNavigationView navView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.navigation_home:
                return true;
            case R.id.navigation_dashboard:
                return true;
            case R.id.navigation_notifications:
                intent = new Intent(AddActivity.this, MeActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        mRect = ((MyApplicationContext) this.getApplicationContext()).getPicData();

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.getMenu().getItem(2).setChecked(true);
    }

    //检查权限
    public int verifyPermissions(Activity activity, String permission) {
        int Permission = ActivityCompat.checkSelfPermission(activity, permission);
        if (Permission == PackageManager.PERMISSION_GRANTED) {
            return 1;
        } else {
            return 0;
        }
    }

    @OnClick({R.id.btn_camera, R.id.btn_album})
    public void onClick(View view) {
        if (verifyPermissions(AddActivity.this, PERMISSIONS_STORAGE[0]) == 0) {
            ActivityCompat.requestPermissions(AddActivity.this, PERMISSIONS_STORAGE, 3);
        } else {
            switch (view.getId()) {
                case R.id.btn_camera:
                    getPicFromCamera();
                    break;
                case R.id.btn_album:
                    getPicFromAlbum();
                    break;
            }
        }
    }

    /**
     * 回调接口
     *
     * @param requestCode
     * @param resultCode
     * @param intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            // 调用相机后返回
            case CAMERA_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = Uri.fromFile(tempFile);
                    Bitmap bitmap = decodeUriAsBitmap(uri);
                    mRect.setmBase(bitmap);
                    Intent intent1 = new Intent(this, Main1Activity.class);
                    startActivityForResult(intent1, CROP_REQUEST_CODE);
                }
                break;
            //调用相册后返回
            case ALBUM_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    Bitmap bitmap = decodeUriAsBitmap(uri);
                    bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/3, bitmap.getHeight()/3, true);
                    mRect.setmBase(bitmap);
                    Intent intent1 = new Intent(this, Main1Activity.class);
                    startActivityForResult(intent1, CROP_REQUEST_CODE);
//                    cropPhoto(uri);
                }
                break;
            case CROP_REQUEST_CODE:
                Bitmap bitmap = mRect.getmBase();
                setAndSaveBitmap(bitmap);
                break;
        }
    }

    private void setAndSaveBitmap(Bitmap bitmap) {
        int width = ScreenUtils.getScreenWidth((Activity) imageView.getContext());

        ViewGroup.LayoutParams vgl = imageView.getLayoutParams();

        if (bitmap == null) {
            return;
        }
        vgl.width = bitmap.getWidth() / 3;
        vgl.height = bitmap.getHeight() / 3;

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setAdjustViewBounds(true);
        imageView.setLayoutParams(vgl);
        imageView.setImageBitmap(bitmap);
        //save
        String path = saveImage(System.currentTimeMillis() + "", bitmap);
        stateText.setText("保存成功！");
    }

    private Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    //相机
    private void getPicFromCamera() {
        tempFile = new File(Environment.getExternalStorageDirectory().getPath(), "test.jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        Uri contentUri = Uri.fromFile(tempFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        startActivityForResult(intent, CAMERA_REQUEST_CODE);
    }

    //相册
    private void getPicFromAlbum() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, ALBUM_REQUEST_CODE);
    }


//    裁剪图片
//
//    private void cropPhoto(Uri uri) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        intent.setDataAndType(uri, "image/*");
//        intent.putExtra("crop", "true");
//        intent.putExtra("aspectX", 0.1);
//        intent.putExtra("aspectY", 0.1);
//        intent.putExtra("outputX", 0);
//        intent.putExtra("outputY", 0);
//        intent.putExtra("return-data", true);
//        intent.putExtra("scale", true);
//        startActivityForResult(intent, CROP_REQUEST_CODE);
//    }

    //保存图片
    public String saveImage(String name, Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory().getPath() + "/data/ustc.meitu/");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 30, fos);
            fos.flush();
            fos.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

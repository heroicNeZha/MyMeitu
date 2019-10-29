package ustc.sse.meitu.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class PictureUtils {

    public static Bitmap getSmallBitmap(File file, int reqWidth, int reqHeight) {
        try {
            String filePath = file.getAbsolutePath();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;//开始读入图片，此时把options.inJustDecodeBounds 设回true了
            BitmapFactory.decodeFile(filePath, options);//此时返回bm为空
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);//设置缩放比例 数值越高，图片像素越低
            options.inJustDecodeBounds = false;//重新读入图片，注意此时把options.inJustDecodeBounds 设回false了
            Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
            //压缩好比例大小后不进行质量压缩
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到bos中
            //压缩好比例大小后再进行质量压缩
            //compressImage(bitmap,filePath);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        try {
            int height = options.outHeight;
            int width = options.outWidth;
            int inSampleSize = 1;  //1表示不缩放
            if (height > reqHeight || width > reqWidth) {
                int heightRatio = Math.round((float) height / (float) reqHeight);
                int widthRatio = Math.round((float) width / (float) reqWidth);
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            }
            return inSampleSize;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}

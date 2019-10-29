package ustc.sse.meitu.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import ustc.sse.meitu.R;
import ustc.sse.meitu.activity.ImageActivity;
import ustc.sse.meitu.pojo.Image;
import ustc.sse.meitu.utils.ScreenUtils;
import ustc.sse.meitu.utils.ToastUtils;

public class LocalImageAdapter extends RecyclerView.Adapter<LocalImageAdapter.ViewHolder> {
    private ArrayList<Image> imageArrayList = new ArrayList<>();


    public void replaceAll(ArrayList<Image> list) {
        imageArrayList.clear();
        if (list != null && list.size() > 0) {
            imageArrayList.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_local, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LocalImageAdapter.ViewHolder holder, int position) {
        Image image = imageArrayList.get(position);
        holder.ivImage.setImageBitmap(image.getBitmap());
        holder.tvDesc.setText(image.getText());
        holder.ivImage.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ImageActivity.class);
            intent.putExtra("image", image.getPath());
            view.getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), view, "sharedView").toBundle());
        });
    }

    @Override
    public int getItemCount() {
        return imageArrayList != null ? imageArrayList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private TextView tvDesc;

        public ViewHolder(View view) {
            super(view);
            ivImage = (ImageView) view.findViewById(R.id.iv_image);
            tvDesc = (TextView) view.findViewById(R.id.tv_desc);
            int width = ScreenUtils.getScreenWidth((Activity) ivImage.getContext());
            ViewGroup.LayoutParams params = ivImage.getLayoutParams();
            //设置图片的相对于屏幕的宽高比
            params.width = width / 2;
            //params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            ivImage.setLayoutParams(params);
        }
    }
}

package ustc.sse.meitu.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import ustc.sse.meitu.R;
import ustc.sse.meitu.pojo.Image;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<Image> imageList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pic;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic_image);
            text = itemView.findViewById(R.id.pic_text);
        }
    }

    public ImageAdapter(List<Image> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Image image = imageList.get(position);
        try {
            FileInputStream fis = new FileInputStream(image.getPath());
            Bitmap bitmap = BitmapFactory.decodeStream(fis);
            viewHolder.pic.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        viewHolder.text.setText(image.getText());
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}

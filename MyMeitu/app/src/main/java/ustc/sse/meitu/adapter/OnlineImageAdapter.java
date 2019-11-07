package ustc.sse.meitu.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.sackcentury.shinebuttonlib.ShineButton;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import ustc.sse.meitu.R;
import ustc.sse.meitu.Service.ImageService;
import ustc.sse.meitu.activity.ImageActivity;
import ustc.sse.meitu.listener.BitmapCallbackListener;
import ustc.sse.meitu.listener.ImageListCallbackListener;
import ustc.sse.meitu.listener.onItemClickListener;
import ustc.sse.meitu.pojo.Image;
import ustc.sse.meitu.pojo.MyApplicationContext;
import ustc.sse.meitu.utils.FileUtils;
import ustc.sse.meitu.utils.HttpUtils;
import ustc.sse.meitu.utils.ToastUtils;

public class OnlineImageAdapter extends RecyclerView.Adapter<OnlineImageAdapter.ViewHolder> {

    private Context context;

    private MyApplicationContext myAppCtx;
    private ImageService imageService;

    private ArrayList<Image> imageArrayList;
    private ArrayList<Image> deleteArrayList;

    private onItemClickListener listener;

    private Random random;
    private Gson gson;
    private static final int COMPLETED = 1;

    public OnlineImageAdapter(Context context) {
        super();
        this.context = context;
        myAppCtx = ((MyApplicationContext) context.getApplicationContext());
        imageService = new ImageService();
        imageArrayList = new ArrayList<>();
        deleteArrayList = new ArrayList<>();
        random = new Random();
        gson = new Gson();
    }

    public void setDeleteArrayList(ArrayList<Image> deleteArrayList) {
        this.deleteArrayList = deleteArrayList;
    }

    //设置监听器
    public void setListener(onItemClickListener listener) {
        this.listener = listener;
    }

    private boolean inDeletionMode = false;

    public void setInDeletionMode(boolean inDeletionMode) {
        this.inDeletionMode = inDeletionMode;
        notifyDataSetChanged();
    }

    public void initImage() {
        imageArrayList.clear();
        imageService.list(myAppCtx.getToken(), new ImageListCallbackListener() {
            @Override
            public void onSuccess(ArrayList<Image> imageList) {
                for (Image image : imageList) {
                    image.setText("图片" + image.getPath().substring(image.getPath().lastIndexOf("/"), image.getPath().lastIndexOf("/") + 13) + "说点什么吧..");
                    imageArrayList.add(image);
                }
                Message msg = new Message();
                msg.what = COMPLETED;
                handler.sendMessage(msg);
            }

            @Override
            public void onError(String exception) {
                ToastUtils.showLong(context, exception);
            }
        });
        notifyDataSetChanged();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == COMPLETED) {
                notifyDataSetChanged();
            }
        }
    };

    public void refreshImage() {
        notifyDataSetChanged();
    }

    @Override
    public OnlineImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        OnlineImageAdapter.ViewHolder viewHolder = new OnlineImageAdapter.ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_local, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OnlineImageAdapter.ViewHolder holder, int position) {
        Image image = imageArrayList.get(position);

        if (image.getBitmap() == null) {
            holder.tvDesc.setText("加载中");
            imageService.get(image, new BitmapCallbackListener() {
                @Override
                public void onSuccess(Bitmap bitmap) {
                    image.setBitmap(bitmap);Message msg = new Message();
                    msg.what = COMPLETED;
                    handler.sendMessage(msg);
                }

                @Override
                public void onError(String exception) {
                    ToastUtils.showLong(context, exception);
                }
            });
        }

        holder.ivImage.setImageBitmap(image.getBitmap());
        holder.tvDesc.setText(image.getText());
        holder.tvLike.setText(String.valueOf(random.nextInt(100)));
        holder.btHeart.setOnClickListener(view -> {
            holder.tvLike.setText(String.valueOf(Integer.valueOf(holder.tvLike.getText().toString()) + 1));
        });

        holder.checkbox.setVisibility(inDeletionMode ? View.VISIBLE : View.GONE);
        //判断删除模式
        if (inDeletionMode) {
            holder.ivImage.setOnClickListener(view -> {
                holder.checkbox.setChecked(!holder.checkbox.isChecked());
            });
            holder.ivImage.setOnLongClickListener(null);
            holder.checkbox.setChecked(deleteArrayList.contains(image));
            holder.checkbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    deleteArrayList.add(image);
                } else {
                    deleteArrayList.remove(image);
                }
            });
        } else {
            holder.ivImage.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), ImageActivity.class);
                intent.putExtra("image", image.getPath());
                view.getContext().startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((Activity) view.getContext(), view, "sharedView").toBundle());
            });
            holder.ivImage.setOnLongClickListener(view -> {
                if (null != listener) {
                    listener.onItemLongClick(image);
                }
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return imageArrayList != null ? imageArrayList.size() : 0;
    }

    //底部按钮
    public void deleteSelected() {
        for (Image img : deleteArrayList) {
            imageArrayList.remove(img);
            FileUtils.deleteFile(img.getPath());
        }
        deleteArrayList.clear();
    }

    public boolean downloadSelected() {
        new Thread() {
            @Override
            public void run() {
                Map<String, String> map = imageService.upload(myAppCtx.getToken(), deleteArrayList);
                System.out.println(map);
            }
        }.start();
        return true;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivImage;
        private TextView tvDesc;
        public CheckBox checkbox;
        public TextView tvLike;
        public ShineButton btHeart;

        public ViewHolder(View view) {
            super(view);
            ivImage = (ImageView) view.findViewById(R.id.iv_image);
            tvDesc = (TextView) view.findViewById(R.id.tv_desc);
            checkbox = (CheckBox) view.findViewById(R.id.cb_delete);
            tvLike = (TextView) view.findViewById(R.id.tv_like);
            btHeart = (ShineButton) view.findViewById(R.id.bt_heart);
        }
    }
}
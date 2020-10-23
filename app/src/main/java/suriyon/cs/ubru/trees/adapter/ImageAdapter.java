package suriyon.cs.ubru.trees.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import suriyon.cs.ubru.trees.R;
import suriyon.cs.ubru.trees.model.Image;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context context;
    private ArrayList<Image> images;

    public ImageAdapter(Context context, ArrayList<Image> images){
        this.context = context;
        this.images = images;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.images_item, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Image image = images.get(position);
        String url = "http://suriyon.cs.ubru.ac.th/ubrutree/images/";
        String treeName = image.getImageName();
        String imgUrl = url + image.getImageUrl();

        holder.tvTreeName.setText(treeName);
        Picasso.get().load(imgUrl).fit().centerInside().into(holder.imgTree);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTree;
        TextView tvTreeName;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTree = itemView.findViewById(R.id.img_tree_image);
            tvTreeName = itemView.findViewById(R.id.tv_image_name);
        }
    }
}

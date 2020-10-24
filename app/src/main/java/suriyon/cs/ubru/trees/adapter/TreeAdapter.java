package suriyon.cs.ubru.trees.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import suriyon.cs.ubru.trees.R;
import suriyon.cs.ubru.trees.ShowImageActivity;
import suriyon.cs.ubru.trees.model.Tree;

public class TreeAdapter extends RecyclerView.Adapter<TreeAdapter.TreeViewHolder> {
    private Context context;
    private ArrayList<Tree> trees;
    public TreeAdapter(Context context, ArrayList<Tree> trees){
        this.context = context;
        this.trees = trees;
    }
    @NonNull
    @Override
    public TreeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trees_item, parent, false);
        return new TreeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TreeViewHolder holder, int position) {
        holder.tvTreeName.setText(trees.get(position).getName());
        holder.tvTreeDescription.setText(trees.get(position).getDescription());
        holder.tvLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowImageActivity.class);
                String id = trees.get(position).getId() + "";
                intent.putExtra("id", id);
                intent.putExtra("name", trees.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return trees.size();
    }

    public class TreeViewHolder extends RecyclerView.ViewHolder {
        TextView tvTreeName, tvTreeDescription, tvLink;
        public TreeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTreeName = itemView.findViewById(R.id.tv_tree_name);
            tvTreeDescription = itemView.findViewById(R.id.tv_tree_description);
            tvLink = itemView.findViewById(R.id.tv_link);
        }
    }
}

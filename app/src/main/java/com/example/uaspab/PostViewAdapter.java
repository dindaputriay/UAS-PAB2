package com.example.uaspab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uaspab.databinding.PostItemBinding;

import java.util.ArrayList;
import java.util.List;

public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.ViewHolder>{

    private List<Post> posts= new ArrayList<>();
    private Context context;
    public PostViewAdapter(Context context) {
        this.context = context;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewAdapter.ViewHolder holder, int position) {
        int posisi = holder.getAdapterPosition();
        Post post = posts.get(posisi);
        holder.tvUsername.setText(post.getNama_anda());
        holder.tvContent.setText(post.getLokasi());
        holder.tvcreatedAt.setText(post.getCreated_date());
        holder.tvKeterangan.setText(post.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername, tvContent, tvcreatedAt, tvKeterangan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tv_username);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvcreatedAt = itemView.findViewById(R.id.tv_created_date);
            tvKeterangan = itemView.findViewById(R.id.tv_keterangan);
        }
    }
}

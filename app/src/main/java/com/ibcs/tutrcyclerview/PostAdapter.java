package com.ibcs.tutrcyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibcs.tutrcyclerview.models.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {

    private Context context;
    private List<Post> postList;
    private LayoutInflater inflater;

    public PostAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.postList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_post,parent,false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post post = postList.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void addPost(Post post){
        this.postList.add(post);
        int position = postList.indexOf(post);
        notifyItemInserted(position);
    }

    class PostHolder extends RecyclerView.ViewHolder{

        TextView tvTitle,tvBody;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.title);
            tvBody = itemView.findViewById(R.id.body);
        }

        public void bind(Post post){
            tvTitle.setText(post.getTitle());
            tvBody.setText(post.getBody());
        }
    }
}

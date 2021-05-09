package com.example.trell_app.Tiles;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trell_app.FullScreenActivity;
import com.example.trell_app.LogInActivity;
import com.example.trell_app.R;
import com.example.trell_app.VideoPlayback.MediaObject;

import java.util.ArrayList;
import java.util.List;

public class ExploreTilesAdapter extends RecyclerView.Adapter<ExploreTilesHolder> {

    ArrayList<MediaObject> postsList;
    Context context;
    LayoutInflater inflater;

    public ExploreTilesAdapter(ArrayList<MediaObject> userList, Context context) {
        this.postsList = userList;
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ExploreTilesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.explore_tiles_item,parent,false);

        return new ExploreTilesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExploreTilesHolder holder, int position) {
        holder.mThumbnailLikes.setText(String.valueOf(postsList.get(position).getNoOfLikes()));
        holder.mThumbnailTitle.setText(postsList.get(position).getTitle());
        holder.mThumbnailUserId.setText(postsList.get(position).getUserName());
        holder.mThumbnail.setImageResource(R.drawable.doggy);

        String thumbnailUrl=postsList.get(position).getThumbnail();

        if(thumbnailUrl!=null&& !thumbnailUrl.equals("")){
            Glide.with(context).load(thumbnailUrl).dontAnimate().into(holder.mThumbnail);
            holder.loading.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context , FullScreenActivity.class);
//                intent.putExtra("postId",postsList.get(position).getPostId());
                intent.putExtra("position",position);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
}

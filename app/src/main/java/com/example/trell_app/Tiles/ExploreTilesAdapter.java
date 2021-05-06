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

import java.util.ArrayList;
import java.util.List;

public class ExploreTilesAdapter extends RecyclerView.Adapter<ExploreTilesHolder> {

    ArrayList<ExploreTilesObject> postsList;
    Context context;
    LayoutInflater inflater;

    public ExploreTilesAdapter(ArrayList<ExploreTilesObject> userList, Context context) {
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
        holder.mThumbnailLikes.setText(String.valueOf(postsList.get(position).noOfLikes));
        holder.mThumbnailTitle.setText(postsList.get(position).title);
        holder.mThumbnailUserId.setText(postsList.get(position).username);
        holder.mThumbnail.setImageResource(R.drawable.doggy);

        String thumbnailUrl=postsList.get(position).thumbnailUrl;

        if(thumbnailUrl!=null&& !thumbnailUrl.equals("")){
            Glide.with(context).load(thumbnailUrl).dontAnimate().into(holder.mThumbnail);
            holder.loading.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context , FullScreenActivity.class);
                intent.putExtra("postId",postsList.get(position).postId);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }
}

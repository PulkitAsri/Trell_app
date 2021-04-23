package com.example.trell_app.Tiles;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trell_app.R;

import java.util.ArrayList;
import java.util.List;

public class ExploreTilesAdapter extends RecyclerView.Adapter<ExploreTilesHolder> {

    ArrayList<ExploreTilesObject> userList;
    Context context;
    LayoutInflater inflater;

    public ExploreTilesAdapter(ArrayList<ExploreTilesObject> userList, Context context) {
        this.userList = userList;
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
        holder.mThumbnailLikes.setText(String.valueOf(userList.get(position).noOfLikes));
        holder.mThumbnailTitle.setText(userList.get(position).title);
        holder.mThumbnailUserId.setText(userList.get(position).username);
        holder.mThumbnail.setImageResource(R.drawable.doggy);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}

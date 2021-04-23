package com.example.trell_app.Tiles;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.trell_app.R;

public class ExploreTilesHolder extends RecyclerView.ViewHolder {
    ImageView mThumbnail ;
    TextView mThumbnailLikes;
    TextView mThumbnailUserId;
    TextView mThumbnailTitle;

    public ExploreTilesHolder(View view){
        super(view);
        mThumbnail=view.findViewById(R.id.thumbnailImage);
        mThumbnailLikes=view.findViewById(R.id.thumbnailLikes);
        mThumbnailUserId=view.findViewById(R.id.thumbnailUserId);
        mThumbnailTitle=view.findViewById(R.id.thumbnailTitle);

    }
}

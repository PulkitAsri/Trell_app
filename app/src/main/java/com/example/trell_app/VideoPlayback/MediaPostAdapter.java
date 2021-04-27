package com.example.trell_app.VideoPlayback;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trell_app.R;

import java.util.List;

public class MediaPostAdapter extends RecyclerView.Adapter<MediaPostAdapter.MediaPostHolder> {


    List<MediaObject> mediaObjectList;
    Context context;

    public MediaPostAdapter(List<MediaObject> mediaObjectList, Context context) {
        this.mediaObjectList = mediaObjectList;
        this.context = context;
    }

    @NonNull
    @Override
    public MediaPostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.video_playback_item,parent,false);

        return new MediaPostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaPostHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mediaObjectList.size();
    }








    //Holder
    public class MediaPostHolder extends RecyclerView.ViewHolder{
        public MediaPostHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

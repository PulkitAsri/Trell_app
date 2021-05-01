package com.example.trell_app.VideoPlayback;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trell_app.R;

import java.sql.PreparedStatement;
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
        holder.setVideoPostData(mediaObjectList.get(position));

    }

    @Override
    public int getItemCount() {
        return mediaObjectList.size();
    }

    //Holder

    public class MediaPostHolder extends RecyclerView.ViewHolder{
        TextView titleTextView;
        TextView descTextView;
        TextView likesTextView;
        TextView commentsTextView;
        TextView userNameTextView;


        ImageButton likeButton;
        ImageButton commentButton;
        ImageButton reactButton;
        ImageButton shareButton;
        VideoView videoView;
        ProgressBar loading;

        public MediaPostHolder(@NonNull View itemView) {
            super(itemView);


            titleTextView = itemView.findViewById(R.id.fullTitleTextView);
            descTextView = itemView.findViewById(R.id.fullDescTextView);
            likesTextView=itemView.findViewById(R.id.fullNoOfLikes);
            commentsTextView=itemView.findViewById(R.id.fullNoOfComments);
            userNameTextView=itemView.findViewById(R.id.fullUsernameTextView);


            likeButton=itemView.findViewById(R.id.likeButton);
            commentButton=itemView.findViewById(R.id.commentButton);
            reactButton=itemView.findViewById(R.id.reactButton);
            shareButton=itemView.findViewById(R.id.shareButton);

            videoView=itemView.findViewById(R.id.videoView);

            loading=itemView.findViewById(R.id.fullLoadingProgressBar);
        }


        void setVideoPostData(MediaObject mediaObject){
            titleTextView.setText(mediaObject.getTitle());
            descTextView.setText(mediaObject.getDesc());
            likesTextView.setText(mediaObject.getNoOfLikes().toString());
            commentsTextView.setText(mediaObject.getNoOfComments().toString());
            userNameTextView.setText(mediaObject.getUserName());

            videoView.setVideoURI(Uri.parse(mediaObject.getMediaUrl()));
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    loading.setVisibility(View.GONE);
                    mp.start();

                    float videoRatio=mp.getVideoWidth()/(float)mp.getVideoHeight();
                    float screenRatio=videoView.getWidth()/(float)videoView.getHeight();
                    float scale=videoRatio/screenRatio;

                    if(scale >=1f) videoView.setScaleX(scale);
                    else videoView.setScaleY(1f/scale);

                    mp.setLooping(true);
                }
            });



        }
    }


}

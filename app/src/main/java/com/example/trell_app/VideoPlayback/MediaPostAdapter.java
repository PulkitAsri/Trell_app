package com.example.trell_app.VideoPlayback;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trell_app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.PreparedStatement;
import java.util.List;

public class MediaPostAdapter extends RecyclerView.Adapter<MediaPostAdapter.MediaPostHolder> {


    List<MediaObject> mediaObjectList;
    Context context;
    String currentUserUid=FirebaseAuth.getInstance().getCurrentUser().getUid();

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
//        holder.likeButton.setOnClickListener(v -> {
//            holder.likeButtonClicked(mediaObjectList.get(position));
//            notifyDataSetChanged();
//
//        });

    }

    @Override
    public int getItemCount() {
        return mediaObjectList.size();
    }


    ////////////////////////////////////HOLDER////////////////////////
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
        ImageView mFullThumbnail;
        VideoView videoView;
        ProgressBar loading;

        public MediaPostHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.fullTitleTextView);
            descTextView = itemView.findViewById(R.id.fullDescTextView);
            likesTextView=itemView.findViewById(R.id.fullNoOfLikes);
            commentsTextView=itemView.findViewById(R.id.fullNoOfComments);
            userNameTextView=itemView.findViewById(R.id.fullUsernameTextView);
            mFullThumbnail=itemView.findViewById(R.id.fullThumbnailImageView);

            videoView=itemView.findViewById(R.id.videoView);
            loading=itemView.findViewById(R.id.fullLoadingProgressBar);

            likeButton=itemView.findViewById(R.id.likeButton);
            commentButton=itemView.findViewById(R.id.commentButton);
            reactButton=itemView.findViewById(R.id.reactButton);
            shareButton=itemView.findViewById(R.id.shareButton);
        }

        private void likeButtonClicked(MediaObject mediaObject) {
            if(mediaObject.isLiked()){
                //unlike the post
                setLikeButtonOff();
                mediaObject.setLiked(false);
                long no=mediaObject.getNoOfLikes()-1;
                mediaObject.setNoOfLikes(no);
                likesTextView.setText(""+no);
                Toast.makeText(context, "Unliked", Toast.LENGTH_SHORT).show();

            }else{
                //like the post
                setLikeButtonOn();
                mediaObject.setLiked(true);
                likesTextView.setText(""+(mediaObject.getNoOfLikes()+1));
                long no=mediaObject.getNoOfLikes()+1;
                mediaObject.setNoOfLikes(no);
                likesTextView.setText(""+no);
                Toast.makeText(context, "liked", Toast.LENGTH_SHORT).show();


            }
        }


        void setVideoPostData(MediaObject mediaObject){
            titleTextView.setText(mediaObject.getTitle());
            descTextView.setText(mediaObject.getDesc());
            likesTextView.setText(String.valueOf(mediaObject.getNoOfLikes()));
            commentsTextView.setText(String.valueOf(mediaObject.getNoOfComments()));
            userNameTextView.setText(mediaObject.getUserName());

            checkIfLiked(mediaObject);

            Glide.with(context).load(mediaObject.getThumbnail()).into(mFullThumbnail);

            videoView.setVideoURI(Uri.parse(mediaObject.getMediaUrl()));
            videoView.setOnPreparedListener(mp -> {
                loading.setVisibility(View.GONE);

                float videoRatio=mp.getVideoWidth()/(float)mp.getVideoHeight();
                float screenRatio=videoView.getWidth()/(float)videoView.getHeight();
                float scale=videoRatio/screenRatio;

                if(scale >=1f) videoView.setScaleX(scale);
                else videoView.setScaleY(1f/scale);

                mp.start();
                mFullThumbnail.setVisibility(View.GONE);
                mp.setLooping(true);
            });

        }
        void checkIfLiked(MediaObject mediaObject){

            DatabaseReference ref= FirebaseDatabase.getInstance().getReference()
                    .child("posts").child(mediaObject.getPostId()).child("likes");
            ref.child(currentUserUid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        mediaObject.setLiked(true);
                        setLikeButtonOn();
                    }else{
                        setLikeButtonOff();
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        void setLikeButtonOn(){
            likeButton.setImageResource(R.drawable.liked_icon);


        }
        void setLikeButtonOff(){
            likeButton.setImageResource(R.drawable.like_icon);


        }
    }


}

package com.example.trell_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.trell_app.VideoPlayback.MediaObject;
import com.example.trell_app.VideoPlayback.MediaPostAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FullScreenActivity extends AppCompatActivity {

    List<MediaObject> mediaObjectList=new ArrayList<>();

    ViewPager2 viewPager2;
    LinearLayoutManager linearLayoutManager;
    MediaPostAdapter adapter;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        mAuth= FirebaseAuth.getInstance();


        viewPager2=findViewById(R.id.fullScreenVideoViewPager);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);



         listenForData();
//        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-man-under-multicolored-lights-1237-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
//        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-waves-in-the-water-1164-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
//        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-portrait-of-a-woman-in-a-pool-1259-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
//        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-young-mother-with-her-little-daughter-decorating-a-christmas-tree-39745-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
//        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-father-and-his-little-daughter-eating-marshmallows-in-nature-39765-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
//        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-man-under-multicolored-lights-1237-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
//        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-waves-in-the-water-1164-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
//        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-portrait-of-a-woman-in-a-pool-1259-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
//        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-young-mother-with-her-little-daughter-decorating-a-christmas-tree-39745-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
//        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-father-and-his-little-daughter-eating-marshmallows-in-nature-39765-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));

        adapter=new MediaPostAdapter(mediaObjectList, getApplicationContext());
        viewPager2.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }

    public void listenForData(){
        DatabaseReference postDbRef= FirebaseDatabase.getInstance().getReference().child("posts");

        postDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String postId=dataSnapshot.getKey();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MediaObject mediaObject = new MediaObject(
                            postId,
                            mAuth.getCurrentUser().getUid(),
                            snapshot.child("thumbnail").getValue().toString(),
                            snapshot.child("mediaUrl").getValue().toString() ,
                            snapshot.child("title").getValue().toString() ,
                            snapshot.child("desc").getValue().toString() ,
                            snapshot.child("date").getValue().toString() ,
                            Long.parseLong(snapshot.child("views").getValue().toString()) ,
                            snapshot.child("userName").getValue().toString() ,
                            snapshot.child("likes").getValue()  ,
                            Long.parseLong(snapshot.child("noOfLikes").getValue().toString()) ,
                            snapshot.child("comments").getValue() ,
                            Long.parseLong(snapshot.child("noOfComments").getValue().toString())
                    );
                    mediaObjectList.add(mediaObject);
                    Log.i("mediaSize", String.valueOf(mediaObjectList.size()));
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
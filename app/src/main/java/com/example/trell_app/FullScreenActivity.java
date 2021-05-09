package com.example.trell_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.trell_app.Fragments.ExploreFragment;
import com.example.trell_app.VideoPlayback.MediaObject;
import com.example.trell_app.VideoPlayback.MediaPostAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullScreenActivity extends AppCompatActivity {

    List<MediaObject> mediaObjectList=new ArrayList<>();

    HashMap<String,MediaObject> postIdMap=new HashMap();

    ViewPager2 viewPager2;
    LinearLayoutManager linearLayoutManager;
    MediaPostAdapter adapter;

    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        mAuth= FirebaseAuth.getInstance();
        Intent intent=getIntent();
//        String selectedPostId=intent.getStringExtra("postId");
         int positionTouched = intent.getExtras().getInt("position");

        Log.i("INTENT"," "+positionTouched);

        mediaObjectList=ExploreFragment.postsList;
//        listenForData();

        viewPager2=findViewById(R.id.fullScreenVideoViewPager);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        adapter=new MediaPostAdapter(mediaObjectList, getApplicationContext());
        viewPager2.setAdapter(adapter);
        viewPager2.setCurrentItem(positionTouched,true);

        adapter.notifyDataSetChanged();



    }

    public List<MediaObject> listenForData(){
        DatabaseReference postDbRef= FirebaseDatabase.getInstance().getReference().child("posts");

        postDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String postId=snapshot.getKey();
                    MediaObject mediaObject = new MediaObject(
                            postId,
                            snapshot.child("userId").getValue().toString(),
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

        return mediaObjectList;


    }
}
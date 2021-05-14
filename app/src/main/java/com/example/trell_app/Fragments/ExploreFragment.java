package com.example.trell_app.Fragments;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trell_app.R;
import com.example.trell_app.Tiles.ExploreTilesAdapter;
import com.example.trell_app.Tiles.ExploreTilesObject;
import com.example.trell_app.VideoPlayback.MediaObject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment  {

    RecyclerView mExploreTiles;
    private ExploreTilesAdapter mAdapter;
    private GridLayoutManager gridLayoutManager;
    FirebaseAuth mAuth;

    public static ArrayList<MediaObject> postsList;


    public static ExploreFragment newInstance(){
        return new ExploreFragment();
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_explore,container,false);

        postsList=new ArrayList<MediaObject>();
        postsList=listenForData();

        mExploreTiles= view.findViewById(R.id.exploreRecyclerView);
        gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        mAdapter=new ExploreTilesAdapter(postsList,getContext());
        mExploreTiles.setAdapter(mAdapter);
        mExploreTiles.setLayoutManager(gridLayoutManager);

        mAuth=FirebaseAuth.getInstance();


        return view;
    }
    public  ArrayList<MediaObject> listenForData(){
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
                    postsList.add(mediaObject);
                }

                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return postsList;
    }

//    private ArrayList<MediaObject> listenForData(){
//        DatabaseReference postDbRef= FirebaseDatabase.getInstance().getReference().child("posts");
//
//        postDbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    String postId=snapshot.getKey();
//                    ExploreTilesObject exploreTilesObject = new ExploreTilesObject(
//                            postId,
//                            snapshot.child("thumbnail").getValue().toString(),
//                            Long.parseLong(snapshot.child("noOfLikes").getValue().toString()),
//                            snapshot.child("userName").getValue().toString(),
//                            snapshot.child("title").getValue().toString()
//                    );
//                    posts.add(exploreTilesObject);
//                    Log.i("postSize", String.valueOf(posts.size()));
//                }
//                mAdapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//        return posts;
//    }
    //    RecyclerView mExploreTiles= View.findViewById(R.id.exploreRecyclerView);

}

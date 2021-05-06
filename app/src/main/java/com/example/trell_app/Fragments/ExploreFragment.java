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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExploreFragment extends Fragment  {

    RecyclerView mExploreTiles;
    private ExploreTilesAdapter mAdapter;
    private GridLayoutManager gridLayoutManager;
    FirebaseAuth mAuth;

    ArrayList<ExploreTilesObject> posts;


    public static ExploreFragment newInstance(){
        ExploreFragment exploreFragment=new ExploreFragment();
        return exploreFragment;
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_explore,container,false);

        posts=new ArrayList<ExploreTilesObject>();
        mExploreTiles= view.findViewById(R.id.exploreRecyclerView);
        gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        mAdapter=new ExploreTilesAdapter(listenForData(),getContext());
        mExploreTiles.setAdapter(mAdapter);
        mExploreTiles.setLayoutManager(gridLayoutManager);

        mAuth=FirebaseAuth.getInstance();


        return view;
    }


    private ArrayList<ExploreTilesObject> listenForData(){
        DatabaseReference postDbRef= FirebaseDatabase.getInstance().getReference().child("posts");

        postDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String postId=snapshot.getKey();
                    ExploreTilesObject exploreTilesObject = new ExploreTilesObject(
                            postId,
                            snapshot.child("thumbnail").getValue().toString(),
                            Long.parseLong(snapshot.child("noOfLikes").getValue().toString()),
                            snapshot.child("userName").getValue().toString(),
                            snapshot.child("title").getValue().toString()
                    );
                    posts.add(exploreTilesObject);
                    Log.i("postSize", String.valueOf(posts.size()));
                }
                mAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return posts;
    }
    //    RecyclerView mExploreTiles= View.findViewById(R.id.exploreRecyclerView);

}

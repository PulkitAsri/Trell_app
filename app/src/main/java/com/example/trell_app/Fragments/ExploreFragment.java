package com.example.trell_app.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trell_app.R;
import com.example.trell_app.Tiles.ExploreTilesAdapter;
import com.example.trell_app.Tiles.ExploreTilesObject;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment {

    RecyclerView mExploreTiles;
    private ExploreTilesAdapter mAdapter;
    private GridLayoutManager gridLayoutManager;

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
        mAdapter=new ExploreTilesAdapter(getDataSet(),getContext());
        mExploreTiles.setAdapter(mAdapter);
        mExploreTiles.setLayoutManager(gridLayoutManager);



        return view;
    }

    private ArrayList<ExploreTilesObject> getDataSet() {
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        posts.add(new ExploreTilesObject("",2324,"elon_musk","CUTTIEEE !"));
        return posts;


    }
    //    RecyclerView mExploreTiles= View.findViewById(R.id.exploreRecyclerView);



}

package com.example.trell_app.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trell_app.R;

public class ExploreFragment extends Fragment {

    public static ExploreFragment newInstance(){
        ExploreFragment exploreFragment=new ExploreFragment();
        return exploreFragment;
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_explore,container,false);
        return view;
    }
    //    RecyclerView mExploreTiles= View.findViewById(R.id.exploreRecyclerView);



}

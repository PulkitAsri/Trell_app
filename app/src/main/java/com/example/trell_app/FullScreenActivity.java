package com.example.trell_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.trell_app.VideoPlayback.MediaObject;
import com.example.trell_app.VideoPlayback.MediaPostAdapter;

import java.util.ArrayList;
import java.util.List;

public class FullScreenActivity extends AppCompatActivity {

    List<MediaObject> mediaObjectList=new ArrayList<>();

    ViewPager2 viewPager2;
    LinearLayoutManager linearLayoutManager;
    MediaPostAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);


        viewPager2=findViewById(R.id.fullScreenVideoViewPager);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);



        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-man-under-multicolored-lights-1237-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-waves-in-the-water-1164-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-portrait-of-a-woman-in-a-pool-1259-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-young-mother-with-her-little-daughter-decorating-a-christmas-tree-39745-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-father-and-his-little-daughter-eating-marshmallows-in-nature-39765-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-man-under-multicolored-lights-1237-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-waves-in-the-water-1164-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-portrait-of-a-woman-in-a-pool-1259-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-young-mother-with-her-little-daughter-decorating-a-christmas-tree-39745-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));
        mediaObjectList.add(new MediaObject("","https://assets.mixkit.co/videos/preview/mixkit-father-and-his-little-daughter-eating-marshmallows-in-nature-39765-large.mp4","Title Here!","Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua","","","","",""));

        adapter=new MediaPostAdapter(mediaObjectList, getApplicationContext());

        viewPager2.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }
}
package com.example.trell_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.analyzer.VerticalWidgetRun;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.trell_app.VideoPlayback.MediaObject;
import com.example.trell_app.VideoPlayback.MediaPostAdapter;

import java.util.ArrayList;
import java.util.List;

public class FullScreenActivity extends AppCompatActivity {

    List<MediaObject> mediaObjectList=new ArrayList<>();

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MediaPostAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);


        recyclerView=findViewById(R.id.fullScreenRecyclerView);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);



        mediaObjectList.add(new MediaObject("","","","","","","","",""));
        mediaObjectList.add(new MediaObject("","","","","","","","",""));
        mediaObjectList.add(new MediaObject("","","","","","","","",""));
        mediaObjectList.add(new MediaObject("","","","","","","","",""));
        mediaObjectList.add(new MediaObject("","","","","","","","",""));
        mediaObjectList.add(new MediaObject("","","","","","","","",""));
        mediaObjectList.add(new MediaObject("","","","","","","","",""));
        mediaObjectList.add(new MediaObject("","","","","","","","",""));
        mediaObjectList.add(new MediaObject("","","","","","","","",""));
        mediaObjectList.add(new MediaObject("","","","","","","","",""));

        adapter=new MediaPostAdapter(mediaObjectList, getApplicationContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        SnapHelper snapHelper=new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        adapter.notifyDataSetChanged();

    }
}
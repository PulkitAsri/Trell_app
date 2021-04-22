package com.example.trell_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    RecyclerView mExploreTiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mExploreTiles=findViewById(R.id.exploreRecyclerView);

    }
}
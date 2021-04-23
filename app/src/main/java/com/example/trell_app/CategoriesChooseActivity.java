package com.example.trell_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class CategoriesChooseActivity extends AppCompatActivity {


    private Button mfood, mrecipies, mreviews, mfashion, mfitness, mtravel, mbeauty, mpersonalcare, mgadgets, msports, mchallenges, mmotivating, mfamily, mmusic, mtech;
    private Button submitButton;

    private boolean[] categories;
    final int[] selected = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        categories = new boolean[15];

        for(int i = 0;i<categories.length;i++){
            categories[i] = false;
        }

        mfood = findViewById(R.id.food);
        mfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[0] = !categories[0];
                toggleButton(mfood, 0);
            }
        });

        mrecipies = findViewById(R.id.recipies);
        mrecipies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[1] = !categories[1];
                toggleButton(mrecipies, 1);
            }
        });

        mreviews = findViewById(R.id.reviews);
        mreviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[2] = !categories[2];
                toggleButton(mreviews, 2);
            }
        });

        mfashion = findViewById(R.id.fashion);
        mfashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[3] = !categories[3];
                toggleButton(mfashion, 3);
            }
        });

        mfitness = findViewById(R.id.fitness);
        mfitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[4] = !categories[4];
                toggleButton(mfitness, 4);
            }
        });

        mtravel = findViewById(R.id.travel);
        mtravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[5] = !categories[5];
                toggleButton(mtravel, 5);
            }
        });

        mbeauty = findViewById(R.id.beauty);
        mbeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[6] = !categories[6];
                toggleButton(mbeauty, 6);
            }
        });

        mpersonalcare = findViewById(R.id.personal_care);
        mpersonalcare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[7] = !categories[7];
                toggleButton(mpersonalcare, 7);
            }
        });

        mgadgets = findViewById(R.id.gadgets);
        mgadgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[8] = !categories[8];
                toggleButton(mgadgets, 8);
            }
        });

        msports = findViewById(R.id.sports);
        msports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[9] = !categories[9];
                toggleButton(msports, 9);
            }
        });

        mchallenges = findViewById(R.id.challenges);
        mchallenges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[10] = !categories[10];
                 toggleButton(mchallenges, 10);
            }
        });

        mmotivating = findViewById(R.id.motivating);
        mmotivating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[11] = !categories[11];
                toggleButton(mmotivating, 11);
            }
        });

        mfamily = findViewById(R.id.family);
        mfamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[12] = !categories[12];
                 toggleButton(mfamily, 12);
            }
        });

        mmusic = findViewById(R.id.music);
        mmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[13] = !categories[13];
                toggleButton(mmusic, 13);
            }
        });

        mtech = findViewById(R.id.tech);
        mtech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[14] = !categories[14];
                toggleButton(mtech, 14);
            }
        });
        submitButton = findViewById(R.id.continueToHome);
        submitButton.setEnabled(false);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected[0] < 3){
                    //toast
                    Toast.makeText(CategoriesChooseActivity.this, "Choose Atleast three categories", Toast.LENGTH_SHORT).show();
                }else{
                    //sign up completed redirect to app
                    Intent intent= new Intent(getApplicationContext(), HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public void toggleButton(Button CategoryX,int idx){
        categories[idx] = !categories[idx];
        if(categories[idx]){
            CategoryX.setBackgroundColor(CategoryX.getResources().getColor(R.color.white));
            CategoryX.setTextColor(CategoryX.getResources().getColor(R.color.black));
        }else{
            CategoryX.setBackgroundColor(CategoryX.getResources().getColor(R.color.black));
            CategoryX.setTextColor(CategoryX.getResources().getColor(R.color.white));
        }
        if(categories[idx]){
            selected[0]++;
        }else{
            selected[0]--;
        }
        submitButton.setEnabled(selected[0]>=3);
    }



//    public void submitButtonClick(){
//
//
//
//    }

}

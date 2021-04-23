package com.example.trell_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class CategoriesChooseActivity extends AppCompatActivity {


    private Button mfood, mrecipies, mreviews, mfashion, mfitness, mtravel, mbeauty, mpersonal ,mcare, mgadgets, msports, mchallenges, mmotivating, mfamily, mmusic, mtech;
    private Button submitButton;

    private boolean[] categories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        categories = new boolean[15];

        for(int i = 0;i<categories.length;i++){
            categories[i] = false;
        }

        mfood = findViewById(R.id.foodButton);
        mfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[0] = !categories[0];
            }
        });

        mrecipies = findViewById(R.id.recipiesButton);
        mrecipies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[1] = !categories[1];
            }
        });

        mreviews = findViewById(R.id.reviewsButton);
        mreviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[2] = !categories[2];
            }
        });

        mfashion = findViewById(R.id.fashionButton);
        mfashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[3] = !categories[3];
            }
        });

        mfitness = findViewById(R.id.fitnessButton);
        mfitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[4] = !categories[4];
            }
        });

        mtravel = findViewById(R.id.travelButton);
        mtravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[5] = !categories[5];
            }
        });

        mbeauty = findViewById(R.id.beautyButton);
        mbeauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[6] = !categories[6];
            }
        });

        mpersonal care = findViewById(R.id.personal_careButton);
        mpersonal care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[7] = !categories[7];
            }
        });

        mgadgets = findViewById(R.id.gadgetsButton);
        mgadgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[8] = !categories[8];
            }
        });

        msports = findViewById(R.id.sportsButton);
        msports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[9] = !categories[9];
            }
        });

        mchallenges = findViewById(R.id.challengesButton);
        mchallenges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[10] = !categories[10];
            }
        });

        mmotivating = findViewById(R.id.motivatingButton);
        mmotivating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[11] = !categories[11];
            }
        });

        mfamily = findViewById(R.id.familyButton);
        mfamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[12] = !categories[12];
            }
        });

        mmusic = findViewById(R.id.musicButton);
        mmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[13] = !categories[13];
            }
        });

        mtech = findViewById(R.id.techButton);
        mtech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {categories[14] = !categories[14];
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonClick();
            }
        });
    }

    public void toggleButton(boolean categoryX){
        if(categoryX){
            categoryX = false;
        }else{
            categoryX = true;
        }
    }



    public void submitButtonClick(){



    }

}
/*

categories :
    food
    recipies
    reviews = false;
    fashion
    fitness
    travel
    beauty
    personl acre
    gadhgets
    sports
    challenges
    motivationg
    faming
    music
    tech
    family
 */
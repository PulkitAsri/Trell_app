package com.example.trell_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trell_app.Fragments.CameraFragment;
import com.example.trell_app.Fragments.ExploreFragment;
import com.example.trell_app.Fragments.NotificationFragment;
import com.example.trell_app.Fragments.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    ViewPager homePager ;
    FragmentPagerAdapter fragmentPagerAdapter;
    private FirebaseAuth mAuth;
    DatabaseReference profileRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        saveUserDataToSharedPreferences();

        homePager=findViewById(R.id.homePager);
        fragmentPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        homePager.setAdapter(fragmentPagerAdapter);
        homePager.setCurrentItem(0);

    }
    public class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return ExploreFragment.newInstance();
                case 1:
                    return CameraFragment.newInstance();
                case 2:
                    return NotificationFragment.newInstance();
                case 3:
                    return ProfileFragment.newInstance();
//            case 4:
//                return ExploreFragment.newInstance();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

    public void saveUserDataToSharedPreferences(){
        mAuth= FirebaseAuth.getInstance();
        String userId=mAuth.getCurrentUser().getUid();

        profileRef= FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child(userId);

        profileRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String profileImageUrl =snapshot.child("profileImageUrl").getValue(String.class);
                String firstName=snapshot.child("firstname").getValue(String.class);
                String lastName=snapshot.child("lastname").getValue(String.class);
                String username=snapshot.child("username").getValue(String.class);


                SharedPreferences sharedpreferences = getApplication().getSharedPreferences("com.example.trell_app", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("userId", userId);
                editor.putString("profileImageUrl", profileImageUrl);
                editor.putString("userName", username);
                editor.putString("firstName", firstName);
                editor.putString("lastName", lastName);
                editor.apply();

                Log.i("TAG",userId+' '+firstName+" "+lastName+" "+username+" "+profileImageUrl);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
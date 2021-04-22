package com.example.trell_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;

import com.example.trell_app.Fragments.CameraFragment;
import com.example.trell_app.Fragments.ExploreFragment;
import com.example.trell_app.Fragments.NotificationFragment;
import com.example.trell_app.Fragments.ProfileFragment;

public class HomeActivity extends AppCompatActivity {

    ViewPager homePager ;
    FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

}
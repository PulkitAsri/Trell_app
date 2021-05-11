package com.example.trell_app.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.trell_app.R;

public class NotificationFragment extends Fragment {

    public static NotificationFragment newInstance(){
        return new NotificationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_notifications,container,false);

        SharedPreferences sharedpreferences = getContext().getSharedPreferences("com.example.trell_app", Context.MODE_PRIVATE);
        String username =sharedpreferences.getString("userName","default");

        Log.i("Username" ,username);
        return view;
    }
}


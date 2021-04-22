package com.example.trell_app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.trell_app.R;

public class NotificationFragment extends Fragment {

    public static NotificationFragment newInstance(){
        NotificationFragment notificationFragment=new NotificationFragment();
        return notificationFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_notifications,container,false);
        return view;
    }
}


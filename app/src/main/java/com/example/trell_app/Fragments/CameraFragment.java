package com.example.trell_app.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.trell_app.R;

public class CameraFragment  extends Fragment {

    public static CameraFragment newInstance(){
        CameraFragment cameraFragment=new CameraFragment();
        return cameraFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_camera,container,false);
        return view;
    }
}


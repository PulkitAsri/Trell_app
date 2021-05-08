package com.example.trell_app.Fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.camera.core.ViewPort;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.fragment.app.Fragment;

import com.example.trell_app.R;
import com.example.trell_app.VideoPlayback.MediaObject;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URI;

public class CameraFragment  extends Fragment {

    ViewPort viewPort;
    private ListenableFuture<ProcessCameraProvider> cameraProviderFuture;

    private static final int PICK_VIDEO_REQUEST=1;
    private Uri videoURI;


    Button chooseVideoButton;
    Button uploadVideoButton;
    ProgressBar loading;

    VideoView videoView;
    MediaController mediaController;



    public static CameraFragment newInstance(){
        CameraFragment cameraFragment=new CameraFragment();
        return cameraFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_camera,container,false);


        //video Things
        videoView=view.findViewById(R.id.previewCameraSelectedVideo);
        mediaController=new MediaController(getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        videoView.start();


        loading=view.findViewById(R.id.uploadVideoLoadingProgressBar);

        chooseVideoButton=view.findViewById(R.id.chooseVideoButton);

        chooseVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseVideo();
            }
        });
        uploadVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadVideo();
            }
        });

        //viewPort = ((PreviewView)view.findViewById(R.id.cameraPreview)).getViewPort();
//
//        cameraProviderFuture.addListener(() -> {
//            try {
//                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
//                bindPreview(cameraProvider);
//            } catch (ExecutionException | InterruptedException e) {
//                // No errors need to be handled for this Future.
//                // This should never be reached.
//            }
//        }, ContextCompat.getMainExecutor(getContext()));
//
//
//        ImageCapture imageCapture = new ImageCapture.Builder().build();
//
//        OrientationEventListener orientationEventListener = new OrientationEventListener(getContext()) {
//            @Override
//            public void onOrientationChanged(int orientation) {
//                int rotation;
//
//                // Monitors orientation values to determine the target rotation value
//                if (orientation >= 45 && orientation < 135) {
//                    rotation = Surface.ROTATION_270;
//                } else if (orientation >= 135 && orientation < 225) {
//                    rotation = Surface.ROTATION_180;
//                } else if (orientation >= 225 && orientation < 315) {
//                    rotation = Surface.ROTATION_90;
//                } else {
//                    rotation = Surface.ROTATION_0;
//                }
//
//                imageCapture.setTargetRotation(rotation);
//            }
//        };
//
//        orientationEventListener.enable();
//


        return view;
    }
    private void chooseVideo(){
        Intent intent=new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_VIDEO_REQUEST);
    }
    private String getFileExt(Uri uri){
        ContentResolver contentResolver= getContext().getContentResolver();
        MimeTypeMap mimeTypeMap=MimeTypeMap.getSingleton();
        return  mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadVideo(){
        loading.setVisibility(View.VISIBLE);
        if(videoURI!=null){

            StorageReference ref= FirebaseStorage.getInstance().getReference().child("videos");
        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_VIDEO_REQUEST && resultCode == -1 && data!=null && data.getData()!=null){
            videoURI=data.getData();

            //video set to video view in the next activity
            videoView.setVideoURI(videoURI);

        }
    }
}


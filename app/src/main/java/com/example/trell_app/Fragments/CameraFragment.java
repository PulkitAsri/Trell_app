package com.example.trell_app.Fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.ViewPort;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.fragment.app.Fragment;

import com.example.trell_app.R;
import com.example.trell_app.UploadingPostActivity;
import com.example.trell_app.VideoPlayback.MediaObject;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

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
    private Uri mediaUrl;


    public static CameraFragment newInstance(){
        CameraFragment cameraFragment=new CameraFragment();
        return cameraFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_camera,container,false);


        //video Things
        videoView=view.findViewById(R.id.previewCameraSelectedVideo);

        loading=view.findViewById(R.id.uploadVideoLoadingProgressBar);

        chooseVideoButton=view.findViewById(R.id.chooseVideoButton);
        uploadVideoButton=view.findViewById(R.id.uploadVideoButton);

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

            long systemMillis=System.currentTimeMillis();
            StorageReference ref= FirebaseStorage.getInstance().getReference()
                    .child("videos")
                    .child(systemMillis + "." + getFileExt(videoURI));


            //UPLOADING TASK
            UploadTask uploadTask=ref.putFile(videoURI);

            uploadTask.addOnProgressListener(taskSnapshot -> {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                Log.d("PROGRESS", "Upload is " + progress + "% done");
            })
            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    loading.setVisibility(View.INVISIBLE);
                    Toast.makeText(getContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
                }
            });


            //RETREIVING THE DOWNLOAD URL

            Task<Uri> urlTask = uploadTask.continueWithTask(task -> {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                // Continue with the task to get the download URL
                return ref.getDownloadUrl();
            }).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    mediaUrl=downloadUri;
                    Log.i("DOWNLOAD URL",downloadUri.toString());

                    //To the next Activity!
                    Intent intent=new Intent(getContext(), UploadingPostActivity.class);
                    intent.putExtra("mediaUrl",mediaUrl);
                    startActivity(intent);


                } else {
                    // Handle failures

                }
            });
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_VIDEO_REQUEST && resultCode == -1 && data!=null && data.getData()!=null){
            videoURI=data.getData();

            //video set to video view in the next activity
            videoView.setVideoURI(videoURI);
            videoView.setOnPreparedListener(mp -> {
                loading.setVisibility(View.GONE);

                //scaling it to a correct size
                float videoRatio=mp.getVideoWidth()/(float)mp.getVideoHeight();
                float screenRatio=videoView.getWidth()/(float)videoView.getHeight();
                float scale=videoRatio/screenRatio;

                if(scale >=1f) videoView.setScaleX(scale);
                else videoView.setScaleY(1f/scale);

                mp.start();
                mp.setLooping(true);
            });

        }
    }


}


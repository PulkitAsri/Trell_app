package com.example.trell_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class UploadingPostActivity extends AppCompatActivity {

    String uploadedMediaUrl;

    //views
    ImageButton chooseThumbnail;
    EditText titleEditText;
    EditText descEditText;
    Button finalUploaded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading_post);

        //setting up views
        chooseThumbnail=findViewById(R.id.postUploadChooseThumbnail);
        titleEditText=findViewById(R.id.postUploadTitle);
        descEditText=findViewById(R.id.postUploadDesc);
        finalUploaded=findViewById(R.id.uploadPostToDbButton);

        //intent information
        Intent intent=getIntent();
        uploadedMediaUrl=intent.getStringExtra("mediaUrl");

        finalUploaded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalUploadedClicked();
            }
        });

    }
    private void finalUploadedClicked() {

        String title= titleEditText.getText().toString();
        String desc= descEditText.getText().toString();

        if(!title.equals("") && !desc.equals("")){
            //prepare everything to be uploaded

            //upload to posts in the db ref

            //upload to users under posts





        }


    }

}
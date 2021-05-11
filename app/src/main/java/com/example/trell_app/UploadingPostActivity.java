package com.example.trell_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UploadingPostActivity extends AppCompatActivity {

    String uploadedMediaUrl;

    Uri selectedImage=null;
    String thumbnailUrl;

    //views
    ImageButton chooseThumbnail;
    EditText titleEditText;
    EditText descEditText;
    Button finalUploaded;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploading_post);

        //setting up views
        chooseThumbnail=findViewById(R.id.postUploadChooseThumbnail);
        titleEditText=findViewById(R.id.postUploadTitle);
        descEditText=findViewById(R.id.postUploadDesc);
        finalUploaded=findViewById(R.id.uploadPostToDbButton);

        //Firebase Stuff
        mAuth=FirebaseAuth.getInstance();

        //intent information
        Intent intent=getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
            uploadedMediaUrl = extras.getString("mediaUrl");
        Log.i("TAG",""+uploadedMediaUrl);

        //listeners and stuff
        finalUploaded.setOnClickListener(v -> finalUploadedClicked());
        chooseThumbnail.setOnClickListener(v -> {
            Intent intent1 =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent1,1);
        });

    }
    private void finalUploadedClicked() {

        String title= titleEditText.getText().toString();
        String desc= descEditText.getText().toString();

        if(!title.equals("") && !desc.equals("")){

            //Initialisations
            Map<String,Object> postInfo=new HashMap<>();
            SharedPreferences sharedpreferences = this.getSharedPreferences("com.example.trell_app", Context.MODE_PRIVATE);



            //Calculations
            long timeStamp=System.currentTimeMillis();
            String currentUid=mAuth.getCurrentUser().getUid();
            String postId=String.valueOf(Long.MAX_VALUE-timeStamp);
            String userName =sharedpreferences.getString("userName","default");


            //DatabaseInitialisations
            DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
            DatabaseReference userRef=ref.child("users").child(currentUid);
            DatabaseReference postRef=ref.child("posts").child(postId);


            postInfo.put( "comments",(new HashMap<String,String>()).put(currentUid,"Start A Conversation!"));
            postInfo.put( "likes",(new HashMap<String,String>()).put(currentUid,"liked"));
            postInfo.put( "date",timeStamp);
            postInfo.put( "desc",desc);
            postInfo.put( "mediaUrl",(uploadedMediaUrl==null)? "default" :uploadedMediaUrl);
            postInfo.put( "noOfComments", 0);
            postInfo.put( "noOfLikes",0);
            postInfo.put( "thumbnail", (thumbnailUrl==null)? "default" :thumbnailUrl);
            postInfo.put( "title", title);
            postInfo.put( "userId",currentUid);
            postInfo.put( "userName",  userName );
            postInfo.put( "views", 0);








            //upload to posts in the db ref
            postRef.updateChildren(postInfo);


            //upload to users under posts
            userRef.child("posts").updateChildren(postInfo)
                    .addOnCompleteListener(task -> {
                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            });


        }else{
            Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            selectedImage=data.getData();

            Glide.with(this).load(selectedImage).into(chooseThumbnail);
            uploadToFirebaseStorage(selectedImage);

        }

    }
    private void uploadToFirebaseStorage(Uri imageUri) {
        if(imageUri==null) return;

        String filename = UUID.randomUUID().toString();
        StorageReference ref = FirebaseStorage.getInstance().getReference().child("/thumbnails").child(filename);

        //Uploading
        UploadTask uploadTask = ref.putFile(imageUri);
        uploadTask.addOnFailureListener(exception -> {
            // Handle unsuccessful uploads
        }).addOnSuccessListener(taskSnapshot -> {

            Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
            ref.getDownloadUrl().addOnSuccessListener(uri -> thumbnailUrl=uri.toString());
        });
    }


}
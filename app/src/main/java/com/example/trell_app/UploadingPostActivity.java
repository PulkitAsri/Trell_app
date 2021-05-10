package com.example.trell_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

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

        final String[] userNameValue = new String[1];


        if(!title.equals("") && !desc.equals("")){

            //Initialisations
            Map postInfo=new HashMap<>();


            //Calculations
            long timeStamp=System.currentTimeMillis();
            String currentUid=mAuth.getCurrentUser().getUid();
            String postId=String.valueOf(Long.MAX_VALUE-timeStamp);

            //DatabaseInitialisations
            DatabaseReference ref=FirebaseDatabase.getInstance().getReference();
            DatabaseReference userRef=ref.child("users").child(currentUid);
            DatabaseReference postRef=ref.child("posts").child(postId);



            userRef.child("username").get().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Toast.makeText(UploadingPostActivity.this, "Uploaded!", Toast.LENGTH_SHORT).show();
                    userNameValue[0] = task.getResult().getValue().toString();
                    Log.i("UsernameTAG",userNameValue[0]);

                }
            });
            Log.i("UsernameTAG",userNameValue[0]);

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
            postInfo.put( "userName", (userNameValue[0]==null)? "default" :userNameValue[0]);
            postInfo.put( "views", 0);


            //upload to posts in the db ref
            postRef.updateChildren(postInfo);


            //upload to users under posts
            Task completed =userRef.child("posts").updateChildren(postInfo);


            //start a new activity
            completed.addOnCompleteListener(task -> {
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
            try {

                Glide.with(this).load(selectedImage).into(chooseThumbnail);
                uploadToFirebaseStorage(selectedImage);
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }

    }
    private void uploadToFirebaseStorage(Uri imageUri) {
        if(imageUri==null) return;

        String filename = UUID.randomUUID().toString();
        StorageReference ref = FirebaseStorage.getInstance().getReference()
                .child("/thumbnails").child(filename);

//            Picasso.get().load(selectedImage).into(circularImageView);
        try {

            Bitmap bitmap =MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(),imageUri);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
            byte[] data = stream.toByteArray();
            ref.putBytes(data)
                    .addOnSuccessListener(taskSnapshot -> {
                        Toast.makeText(getApplicationContext(), "Uploaded", Toast.LENGTH_SHORT).show();
                        ref.getDownloadUrl().addOnSuccessListener(uri -> thumbnailUrl=uri.toString());

                    });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
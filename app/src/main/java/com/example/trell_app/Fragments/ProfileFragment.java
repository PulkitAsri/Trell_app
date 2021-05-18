package com.example.trell_app.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import com.bumptech.glide.Glide;
import com.example.trell_app.MainActivity;
import com.example.trell_app.R;
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
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ProfileFragment extends Fragment {

    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    ImageButton chooseProfilePicButton;
    CircularImageView circularImageView;
    public FirebaseAuth mAuth;
    DatabaseReference profileRef;
    TextView mName,mPostCount,mFollowers,mFollowing,mUsername;
    Button mLogout;


    DrawerLayout mDrawerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_profile,container,false);

        SharedPreferences sharedpreferences = getContext().getSharedPreferences("com.example.trell_app", Context.MODE_PRIVATE);
        mAuth=FirebaseAuth.getInstance();

        mDrawerLayout=view.findViewById(R.id.drawer_layout);

        view.findViewById(R.id.profileSideBar).setOnClickListener(v -> {
            mDrawerLayout.openDrawer(GravityCompat.END);
        });


        chooseProfilePicButton=view.findViewById(R.id.profilePictureButton);
        circularImageView=view.findViewById(R.id.profilePicture);
        mName=view.findViewById(R.id.profileName);
        mUsername=view.findViewById(R.id.profileUserName);
        mLogout=view.findViewById(R.id.logoutButton);
        mPostCount=view.findViewById(R.id.profilePostCount);
        mFollowers=view.findViewById(R.id.profileFollowers);
        mFollowing=view.findViewById(R.id.profileFollowing);
        String userName =sharedpreferences.getString("userName","default");
        String firstName =sharedpreferences.getString("firstName","default");
        String lastName =sharedpreferences.getString("lastName","default");
        String imageUrl =sharedpreferences.getString("profileImageUrl","default");

        mName.setText(firstName+" "+lastName);
        mUsername.setText(userName);
        Glide.with(getContext()).load(imageUrl).dontAnimate().into(circularImageView);


        chooseProfilePicButton.setOnClickListener(v -> {
            Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent,1);
        });

        mLogout.setOnClickListener(v -> logout());
        return view;
    }

    Uri selectedImage=null;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            selectedImage=data.getData();
            Glide.with(getContext()).load(selectedImage).dontAnimate().into(circularImageView);
            uploadToFirebaseStorage();
        }
    }
    private void saveDataToDatabase(String profileImageUrl ) {

        FirebaseDatabase.getInstance().getReference().child("users")
               .child(mAuth.getCurrentUser().getUid())
                .child("profileImageUrl")
                .setValue(profileImageUrl)
                .addOnSuccessListener(aVoid -> Log.i("SUCCESS","profilePicUploaded"));
        }

    private void uploadToFirebaseStorage() {
        if(selectedImage==null) return;

        String filename = UUID.randomUUID().toString();
        StorageReference ref = FirebaseStorage.getInstance().getReference()
                .child("profilePictures").child(filename);

        Glide.with(this).load(selectedImage).into(circularImageView);

        //Uploading
        UploadTask uploadTask = ref.putFile(selectedImage);
        uploadTask.addOnFailureListener(exception -> {
            // Handle unsuccessful uploads
        }).addOnSuccessListener(taskSnapshot -> {

            Toast.makeText(getContext(), "Uploaded", Toast.LENGTH_SHORT).show();
            ref.getDownloadUrl().addOnSuccessListener(uri ->saveDataToDatabase(uri.toString()));
        });
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
        Log.i("logout "," here");

        SharedPreferences sharedpreferences = getContext().getSharedPreferences("com.example.trell_app", Context.MODE_PRIVATE);
        sharedpreferences.edit().clear();
        Intent intent =new Intent(getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}


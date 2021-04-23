package com.example.trell_app.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.trell_app.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ProfileFragment extends Fragment {

    public static ProfileFragment newInstance(){
        ProfileFragment profileFragment=new ProfileFragment();
        return profileFragment;
    }


    ImageButton chooseProfilePicButton;
    CircularImageView circularImageView;
    public FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_profile,container,false);

        chooseProfilePicButton=view.findViewById(R.id.profilePictureButton);
        circularImageView=view.findViewById(R.id.profilePicture);
        mAuth=FirebaseAuth.getInstance();
        chooseProfilePicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });

        return view;
    }

    Uri selectedImage=null;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            selectedImage=data.getData();

            try {
                Bitmap bitmap =MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),selectedImage);
                circularImageView.setImageBitmap(bitmap);
                uploadToFirebaseStorage();

            } catch ( Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void saveDataToDatabase(String profileImageUrl ) {

        Task<Void> ref= FirebaseDatabase.getInstance().getReference().child("users")
               .child(mAuth.getCurrentUser().getUid())
                .child("profileImageUrl")
                .setValue(profileImageUrl)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.i("SUCCESS","profilePicUploaded");
                    }
                });

        }

    private void uploadToFirebaseStorage() {
        if(selectedImage==null) return;



        String filename = UUID.randomUUID().toString();

        StorageReference ref = FirebaseStorage.getInstance().getReference()
                .child("profilePictures").child(filename);
        try {
            Bitmap bitmap =MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),selectedImage);
        

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
        byte[] data = stream.toByteArray();
        StorageTask<UploadTask.TaskSnapshot> uploadTask = ref.putBytes(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                saveDataToDatabase(uri.toString()) ;
                            }
                        });

                    }
                });

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}


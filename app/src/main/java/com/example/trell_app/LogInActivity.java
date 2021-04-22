package com.example.trell_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInActivity extends AppCompatActivity {

    private Button mLoginButton;
    private EditText mUsername,mPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mUsername=findViewById(R.id.loginUsernameEditText);
        mPassword=findViewById(R.id.loginPasswordEditText);
        mLoginButton=findViewById(R.id.loginButton);

        firebaseAuthStateListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if(user !=null){

                }
            }
        };

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });







    }
}
package com.example.trell_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private Button mSignUpButton;
    private EditText mUsername,mPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUsername=findViewById(R.id.signupUsernameEditText);
        mPassword=findViewById(R.id.signupPasswordEditText);
        mSignUpButton=findViewById(R.id.signupButton);

        mAuth=FirebaseAuth.getInstance();

        firebaseAuthStateListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                Log.i("Auth Starte","");
                if(user!=null){
                    Log.i("Auth Starte in ","");
                    Intent intent= new Intent(getApplicationContext(), HomeActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();

                }
            }
        };

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpClicked();
            }
        });
    }
    public void signUpClicked(){
        String email=mUsername.getText().toString();
        String password=mPassword.getText().toString();
        Log.i("herhehhehe",email+"/"+password);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Sign In Failed", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Welcome To Trell Family !", Toast.LENGTH_SHORT).show();
                    //add to data base

                    String userId=mAuth.getCurrentUser().getUid();


                }
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthStateListner);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthStateListner);
    }
}
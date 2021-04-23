package com.example.trell_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText dateTXT;
    ImageView cal;
    private Button mSignUpButton;
    EditText mFirstName, mLastName, mEmail, mUsername, mPassword, mConfirmPassword;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthStateListner;
    private int mDate, mMonth, mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mEmail=findViewById(R.id.signupEmailEditText);
        mSignUpButton=findViewById(R.id.signupButton);
        dateTXT= findViewById(R.id.date);
        cal = findViewById(R.id.datepicker);
        mFirstName=findViewById(R.id.signupFirstnameEditText);
        mLastName=findViewById(R.id.signupLastnameEditText);
        mUsername=findViewById(R.id.signupUsernameEditText);
        mPassword=findViewById(R.id.signupPasswordEditText);
        mConfirmPassword= findViewById(R.id.signupConfirmPasswordEditText);


        mAuth=FirebaseAuth.getInstance();

        firebaseAuthStateListner=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                Log.i("Auth Started","");
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


        cal.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               calendarClicked();
           }
        });


        dateTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarClicked();
            }
        });
    }

    public void calendarClicked(){
        Calendar Cal = Calendar.getInstance();
        mDate = Cal.get(Calendar.DATE);
        mMonth = Cal.get(Calendar.MONTH);
        mYear = Cal.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(SignUpActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                dateTXT.setText(dayOfMonth+"/"+(month+1)+"/"+year);       //month starting from 0
            }
        }, mYear, mMonth, mDate);
        datePickerDialog.show();
    }

    public void signUpClicked(){
        String email=mEmail.getText().toString();
        String firstname=mFirstName.getText().toString();
        String lastname=mLastName.getText().toString();
        String password=mPassword.getText().toString();
        String confirmpassword= mConfirmPassword.getText().toString();
        String username=mUsername.getText().toString();
        String dob=dateTXT.getText().toString();

        if(!firstname.equals("") && !lastname.equals("") && !password.equals("") && !username.equals("") && password.equals(confirmpassword)) {
            Log.i("herhehhehe", email + "/" + password);

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Sign In Failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Welcome To Trell Family !", Toast.LENGTH_SHORT).show();
                        //add to data base

                        String userId = mAuth.getCurrentUser().getUid();
                        DatabaseReference currentUserDb= FirebaseDatabase.getInstance().getReference().child("users").child(userId);

                        Map userInfo=new HashMap<>();
                        userInfo.put( "email",email);
                        userInfo.put( "firstname",firstname);
                        userInfo.put( "lastname",lastname);
                        userInfo.put( "profileImageUrl", "default");
                        userInfo.put( "dob",dob);
                        userInfo.put( "username", username);
                        currentUserDb.updateChildren(userInfo);

                    }

                }
            });
        }

        else{
            Toast.makeText(getApplicationContext(), "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show();
        }

    }

    HashSet<String> userNamesSet=new HashSet<String>();
    public boolean checkUniqueUserName(String userName){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
        if(userNamesSet.contains(userName)){
            return false;
        }else{
            return true;
        }
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
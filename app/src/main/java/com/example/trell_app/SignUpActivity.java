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

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {

    EditText dateTXT;
    ImageView cal;
    private Button mSignUpButton;
    EditText mFirstName, mLastName, mEmail, mPassword, mConfirmPassword;
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
        mPassword=findViewById(R.id.signupPasswordEditText);
        mConfirmPassword= findViewById(R.id.signupConfirmPasswordEditText);


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

        if(!firstname.equals("") && !lastname.equals("") && !password.equals("") && password.equals(confirmpassword)) {
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


                    }
                }
            });
        }

        else{
            Toast.makeText(getApplicationContext(), "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show();
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
package com.example.trell_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public static Boolean started=false;
    private FirebaseAuth mAuth;
    Button goToSignUpButton;
    Button goToLogInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToLogInButton=findViewById(R.id.goToLogin);
        goToSignUpButton=findViewById(R.id.goToSignUp);


        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null){
            gotoHomeActivity();
        }

        goToSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUp();
            }
        });
        goToLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLogIn();
            }
        });
    }
    public void goToSignUp(){
        Intent intent= new Intent(this ,SignUpActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    public void goToLogIn(){
        Intent intent= new Intent(this ,LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    public void gotoHomeActivity(){
        Intent intent= new Intent(this , HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
//delay of 2 sec and automatically to home ==> signup
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent=new Intent(MainActivity.this, HomeActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        },2000);
//to be replaced by automatic login
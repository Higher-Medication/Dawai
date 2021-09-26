package com.example.dawaii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SecondActivity extends AppCompatActivity {

    ImageView logo, splashImg;
    TextView appName;
    LottieAnimationView lottieAnimationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        logo=findViewById(R.id.logo);
        appName = findViewById(R.id.app_name);
//        splashImg = findViewById(R.id.img);
//        lottieAnimationView = findViewById(R.id.lottie);

//        splashImg.animate().translationY(-1000).setDuration(1000).setStartDelay(4000);
//        logo.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
//        appName.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
//        lottieAnimationView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);


        Button signupBtn = findViewById(R.id.signupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MOVE TO ANOTHER ACTIVITY
                Intent goToSignIn = new Intent(SecondActivity.this, SignUp.class);
                startActivity(goToSignIn);

            }
        });


        Button signinBtn = findViewById(R.id.signinBtn);
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MOVE TO ANOTHER ACTIVITY
                Intent goToSignIn = new Intent(SecondActivity.this, SignIn.class);
                startActivity(goToSignIn);

            }
        });



            }
        }
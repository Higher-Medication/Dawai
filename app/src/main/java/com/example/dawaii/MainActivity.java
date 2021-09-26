package com.example.dawaii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Date;
import com.amplifyframework.datastore.generated.model.Medicine;
import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean isSignedIn;
    String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }


        Button getStartedBtn= findViewById(R.id.getStartedBtn);

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToStartedActivity= new Intent(MainActivity.this , SecondActivity.class);
                startActivity(goToStartedActivity);
            }
        });
//        Button signInBtn = findViewById(R.id.signIn);
//
//        signInBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // MOVE TO ANOTHER ACTIVITY
//                Intent goToSignIn = new Intent(MainActivity.this, SignUp.class);
//                startActivity(goToSignIn);
//
//            }
//        });

 //        User user = User.builder()
//                .name("saify")
//                .dateOfBirth()
//                .meds(medicine)
//                .build();
//
//        List times = new ArrayList();
//        times.add("8:00AM");
//
//        Medicine medicine = Medicine.builder()
//                .name("panadol")
//                .times(times)
//                .numberOfTablets(24)
//                .user(user)
//                .date(date)
//                .build();
//
//
//        Date date = Date.builder()
//                .date()
//                .meds(medicine)
//                .build();

        //fetching userData
//        Amplify.Auth.fetchAuthSession(
//                result -> {
//                    Log.i("AmplifyQuickstart", result.toString());
//                    isSignedIn = result.isSignedIn();
 //
//        Button loginBtn = findViewById(R.id.loginBtn);
//
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // MOVE TO ANOTHER ACTIVITY
//                Intent goToSignIn = new Intent(MainActivity.this, SignIn.class);
//                startActivity(goToSignIn);
//
//            }
//        });


//        Button signOutBtn = findViewById(R.id.signOut);
//        signOutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Amplify.Auth.signOut(
//                        () -> {
//                            Log.i("AuthQuickstart", "Signed out successfully");
//                            Intent sinOut = new Intent(MainActivity.this, SignIn.class);
//                            startActivity(sinOut);
//                        },
//                        error -> Log.e("AuthQuickstart", error.toString())
//                );
//                Intent signOut = new Intent(MainActivity.this, SignIn.class);
//                startActivity(signOut);
//                Toast.makeText(getApplicationContext(), "Signed out successfully !", Toast.LENGTH_LONG).show();
//            }
//        });

    }
}
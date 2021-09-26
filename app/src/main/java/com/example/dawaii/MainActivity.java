package com.example.dawaii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignOutOptions;
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

        Amplify.Auth.signInWithWebUI(
                this,
                result -> Log.i("AuthQuickStart", result.toString()),
                error -> Log.e("AuthQuickStart", error.toString())
        );

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            Amplify.Auth.signOut(
                    () -> {
                        Log.i("AuthQuickstart", "Signed out successfully");
                        finish();
                        startActivity(getIntent());
                    },
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });

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
//                    if (isSignedIn) {
//                        userName = Amplify.Auth.getCurrentUser().getUsername();
////                        TextView welcome = findViewById(R.id.welcomeMsg);
////                        welcome.setText(" هلا والله " + userName);
////                        findViewById(R.id.login).setVisibility(View.INVISIBLE);
////                        findViewById(R.id.logout).setVisibility(View.VISIBLE);
//                    } else {
////                        findViewById(R.id.logout).setVisibility(View.INVISIBLE);
////                        findViewById(R.id.login).setVisibility(View.VISIBLE);
//                    }
//                },
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );


    }
}
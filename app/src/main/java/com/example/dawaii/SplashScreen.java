package com.example.dawaii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;

public class SplashScreen extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                try {
                    Amplify.addPlugin(new AWSApiPlugin());
                    Amplify.addPlugin(new AWSCognitoAuthPlugin());
                    Amplify.configure(getApplicationContext());
                    Log.i("MyAmplifyApp", "Initialized Amplify");
                    Amplify.Auth.fetchAuthSession(
                            result -> {
                                Log.i("AmplifyQuickstart", result.toString());

                                if (result.isSignedIn()) {
                                    Intent mainIntent = new Intent(SplashScreen.this,Calendar.class);
                                    SplashScreen.this.startActivity(mainIntent);
                                    SplashScreen.this.finish();
                                } else {
                                    Intent mainIntent = new Intent(SplashScreen.this,MainActivity.class);
                                    SplashScreen.this.startActivity(mainIntent);
                                    SplashScreen.this.finish();
                                }
                            },
                            error -> Log.e("AmplifyQuickstart", error.toString())
                    );
                    Log.i("MyAmplifyApp", "Initialized Amplify");
                } catch (AmplifyException error) {
                    Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
                }


                /* Create an Intent that will start the Menu-Activity. */

            }
        }, SPLASH_DISPLAY_LENGTH);
    }

}
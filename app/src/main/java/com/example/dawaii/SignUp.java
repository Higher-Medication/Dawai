package com.example.dawaii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Medicine;
import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        try {
//            Amplify.addPlugin(new AWSApiPlugin());
//            Amplify.addPlugin(new AWSCognitoAuthPlugin());
//            Amplify.configure(getApplicationContext());
//            Log.i("MyAmplifyApp", "Initialized Amplify");
//        } catch (AmplifyException error) {
//            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
//        }

        EditText usernameText = findViewById(R.id.UserNameEditText);
        EditText emailText = findViewById(R.id.EmailEditText);
        EditText passwordText = findViewById(R.id.PasswordEditText);

        Button signUpBtn = findViewById(R.id.SignUpButton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                AuthSignUpOptions options = AuthSignUpOptions.builder()
                        .userAttribute(AuthUserAttributeKey.email(), email)
                        .build();
                Amplify.Auth.signUp(username, password, options,
                        result -> {
                            Log.i("AuthQuickStart", "Result: " + result.toString());
                            User user = User.builder()
                                    .name(username)
                                    .build();

                            Amplify.API.mutate(
                                    ModelMutation.create(user),
                                    response -> Log.i("MyAmplifyApp", "Added user with id: " + response.getData().getId()),
                                    error -> Log.e("MyAmplifyApp", "Create failed", error)
                            );
                            ///testtttttttt

//                            List<String> times = new ArrayList();
//                            times.add("00:00:00");
//                            times.add("08:00:00");
//                            times.add("18:00:00");
//
//                            List<String> dates = new ArrayList();
//                            dates.add("2021-09-26");
//                            dates.add("2021-09-27");
//                            dates.add("2021-09-28");

//                            Medicine medicine = Medicine.builder()
//                                    .name("mansaf")
//                                    .times(times)
//                                    .dates(dates)
//                                    .availableTablets(24)
//                                    .dosage(1)
//                                    .expirationDate("2025-09-28")
//                                    .requiredTimes(3)
//                                    .user(user)//signed in user***
//                                    .build();

//                            Amplify.API.mutate(
//                                    ModelMutation.create(medicine),
//                                    response -> Log.i("MyAmplifyApp", "Added medicine with id: " + response.getData().getId()),
//                                    error -> Log.e("MyAmplifyApp", "Create failed", error)
//                            );


                        },
                        error -> Log.e("AuthQuickStart", "Sign up failed", error)
                );

                Intent goToConfirmationActivity = new Intent(SignUp.this, ConfirmSignUp.class);
                goToConfirmationActivity.putExtra("username", username);
                startActivity(goToConfirmationActivity);
            }
        });
    }
}
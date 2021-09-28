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
    boolean isAllFieldsChecked = false;
    EditText usernameText;
    EditText emailText;
    EditText passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        usernameText = findViewById(R.id.UserNameEditText);
        emailText = findViewById(R.id.EmailEditText);
        passwordText = findViewById(R.id.PasswordEditText);


        Button signUpBtn = findViewById(R.id.SignUpButton);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameText.getText().toString();
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                isAllFieldsChecked = CheckAllFields();

                if (isAllFieldsChecked) {
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
                            },
                            error -> Log.e("AuthQuickStart", "Sign up failed", error)
                    );
                    Intent goToConfirmationActivity = new Intent(SignUp.this, ConfirmSignUp.class);
                    goToConfirmationActivity.putExtra("username", username);
                    startActivity(goToConfirmationActivity);
                    SignUp.this.finish();
                }
            }
        });
    }

    private boolean CheckAllFields() {
        if (usernameText.length() == 0) {
            usernameText.setError("This field is required");
            return false;
        }
        if (emailText.length() == 0) {
            emailText.setError("Email is required");
            return false;
        }
        if (passwordText.length() == 0) {
            passwordText.setError("This field is required");
            return false;
        }
        return true;
    }
}
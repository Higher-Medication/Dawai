package com.example.dawaii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;

public class SignIn extends AppCompatActivity {
    boolean isAllFieldsChecked = false;
    EditText signInEmail;
    EditText signInPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        signInEmail = findViewById(R.id.email);
        signInPass = findViewById(R.id.pass);

        Button SignInBtn = findViewById(R.id.buttonSignIn);
        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signInEmail.getText().toString();
                String password = signInPass.getText().toString();

                isAllFieldsChecked = CheckAllFields();

                if (isAllFieldsChecked) {
                    Amplify.Auth.signIn(
                            email,
                            password,
                            result -> {
                                Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                                Intent goToMainActivity = new Intent(SignIn.this, MainActivity.class);
                                startActivity(goToMainActivity);
                            },
                            error -> {
                                Log.e("AuthQuickstart", error.toString());
//                                Toast.makeText(getApplicationContext(),"sdsdsd",Toast.LENGTH_SHORT).show();
                            }
                    );
                }
            }
        });

        TextView forgetPassword = findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToForgotPassword = new Intent(SignIn.this, ForgotPassword.class);
                startActivity(goToForgotPassword);
            }
        });
    }

    private boolean CheckAllFields() {
        if (signInEmail.length() == 0) {
            signInEmail.setError("This field is required");
            return false;
        }
        if (signInPass.length() == 0) {
            signInPass.setError("This field is required");
            return false;
        }
        return true;
    }
}
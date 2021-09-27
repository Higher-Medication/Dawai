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

import com.amplifyframework.core.Amplify;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        EditText signInEmail = findViewById(R.id.email);
        EditText signInPass  = findViewById(R.id.pass);

        Button SignInBtn = findViewById(R.id.buttonSignIn);
        SignInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = signInEmail.getText().toString();
                String password = signInPass.getText().toString();
                Amplify.Auth.signIn(
                        email,
                        password,
                        result -> {
                            Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                            Intent goToMainActivity = new Intent(SignIn.this,MainActivity.class);
                            startActivity(goToMainActivity);
                        },
                        error -> {
                            Log.e("AuthQuickstart", error.toString());
//                            Toast.makeText(getApplicationContext(), "Enter the correct password!", Toast.LENGTH_LONG).show();
                        }
                );
            }
        });

//        Button RegisterBtn = findViewById(R.id.RegisterBtn);
//        RegisterBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goToSignUpActivity = new Intent(SignIn.this,SignUp.class);
//                startActivity(goToSignUpActivity);
//            }
//        });


        TextView forgetPassword = findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToForgotPassword = new Intent(SignIn.this,ForgotPassword.class);
                startActivity(goToForgotPassword);
            }
        });

    }
}
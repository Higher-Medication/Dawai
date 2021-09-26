package com.example.dawaii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.core.Amplify;

public class ConfirmSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_sign_up);

        Intent intent = getIntent();
        String username = intent.getExtras().getString("username");
//        TextView userNameTextView = findViewById(R.id.UserNameTextView);
//        userNameTextView.setText(username);

        EditText confirmationCodeEditText = findViewById(R.id.confirmationCodeEditText);

        Button confirmButton = findViewById(R.id.ConfirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String confirmationCode = confirmationCodeEditText.getText().toString();
                Amplify.Auth.confirmSignUp(
                        username,
                        confirmationCode,
                        result -> {Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
                            Intent goToSignInActivity = new Intent(ConfirmSignUp.this,SignIn.class);
                            startActivity(goToSignInActivity);
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );

            }
        });

    }
}
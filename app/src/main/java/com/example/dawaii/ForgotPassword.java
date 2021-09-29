package com.example.dawaii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);



        EditText resetEmailEditText = findViewById(R.id.resetEmailEditText);

        Button ContinueButton = findViewById(R.id.ContinueButton);
        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = resetEmailEditText.getText().toString();

                Amplify.Auth.resetPassword(
                        username,
                        result -> {Log.i("AuthQuickstart", result.toString());
                        Intent goToConfirmResetPassword = new Intent(ForgotPassword.this,ConfirmResetPassword.class);
                        startActivity(goToConfirmResetPassword);
            },
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(view -> {
//            Intent intent=new Intent(MainActivity.this,AddMedicineActivity.class);
            Intent intent = new Intent(ForgotPassword.this, SignIn.class);
            startActivity(intent);
        });

    }
}
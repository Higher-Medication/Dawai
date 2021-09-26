package com.example.dawaii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amplifyframework.core.Amplify;

public class ConfirmResetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_reset_password);

        EditText newPassword = findViewById(R.id.newPassword);
        EditText newResetCode = findViewById(R.id.newResetCode);


        Button resetPasswordBtn = findViewById(R.id.resetPasswordBtn);
        resetPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = newPassword.getText().toString();
                String code = newResetCode.getText().toString();

                Amplify.Auth.confirmResetPassword(
                        password,
                        code,
                        () -> {
                            Log.i("AuthQuickstart", "New password confirmed");
                            Intent goToSignin = new Intent(ConfirmResetPassword.this,SignIn.class);
                            startActivity(goToSignin);
                        },
                        error -> Log.e("AuthQuickstart", error.toString())
                );
            }
        });
    }
}
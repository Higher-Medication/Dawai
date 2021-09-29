package com.example.dawaii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignOutOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Medicine;
import com.amplifyframework.datastore.generated.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    String userName;
    User currentUser;
    List<Medicine> meds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        RecyclerView profileRecyclerView = findViewById(R.id.profileRecyclerView);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Handler newHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                profileRecyclerView.setAdapter(new ProfileAdapter(meds));
                profileRecyclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        Amplify.Auth.fetchAuthSession(
                result -> {
                    Log.i("AmplifyQuickstart", result.toString());
                    userName = Amplify.Auth.getCurrentUser().getUsername();
                    Amplify.API.query(
                            ModelQuery.list(User.class, User.NAME.contains(userName)),
                            response -> {
                                Log.i("TestLogin", response.getData().toString());
                                for (User item : response.getData().getItems()) {
                                    currentUser = item;
                                }
                                meds = currentUser.getMeds();
                                System.out.println(currentUser);
                                System.out.println("The meds: " + meds);

                                newHandler.sendEmptyMessage(1);

                            },
                            error -> Log.e("MyAmplifyApp", "Query failure", error)
                    );
                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );

        FloatingActionButton logout=findViewById(R.id.logout);
        logout.setOnClickListener(view -> {
            Amplify.Auth.signOut(
                    AuthSignOutOptions.builder().globalSignOut(true).build(),
                    () -> {
                        Log.i("AuthQuickstart", "Signed out successfully");
                        Intent mainIntent = new Intent(Profile.this,MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    },
                    error -> Log.e("AuthQuickstart", error.toString())
            );
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        RecyclerView profileRecyclerView = findViewById(R.id.profileRecyclerView);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileRecyclerView.setAdapter(new ProfileAdapter(meds));
    }
}

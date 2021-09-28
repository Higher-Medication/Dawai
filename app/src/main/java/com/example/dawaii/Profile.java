package com.example.dawaii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Medicine;
import com.amplifyframework.datastore.generated.model.User;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    String userName;
    User currentUser;
    List<Medicine> meds= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        RecyclerView profileRecyclerView = findViewById(R.id.profileRecyclerView);

        Handler newhandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
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
                                System.out.println("my meds " + meds);
                                newhandler.sendEmptyMessage(1);
                            },
                            error -> Log.e("MyAmplifyApp", "Query failure", error)
                    );
                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
//        profileRecyclerView.setLayoutManager(new LinearLayoutManager(Profile.this));
//        profileRecyclerView.setAdapter(new ProfileAdapter(meds));
    }

    @Override
    protected void onStart() {
        super.onStart();
        RecyclerView profileRecyclerView = findViewById(R.id.profileRecyclerView);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileRecyclerView.setAdapter(new ProfileAdapter(meds));

    }
}

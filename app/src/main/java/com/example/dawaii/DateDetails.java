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

public class DateDetails extends AppCompatActivity {
    String userName;
    User currentUser;
    String selctedDate = "09/28/2021";
    List<Medicine> AvailableMed = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_details);
        RecyclerView medsRecyclerView = findViewById(R.id.recyclerView);

        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                medsRecyclerView.getAdapter().notifyDataSetChanged();
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
                                for (Medicine med : currentUser.getMeds()) {
                                    for (String date : med.getDates()) {
                                        if (date.equals(selctedDate)) {
                                            AvailableMed.add(med);
                                            System.out.println("medicine.getName" + med.getName());
                                            break;
                                        }
                                    }
                                }
                                handler.sendEmptyMessage(1);

                            },
                            error -> Log.e("MyAmplifyApp", "Query failure", error)
                    );
                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
        medsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        medsRecyclerView.setAdapter(new MedAdapter(AvailableMed));

    }
}


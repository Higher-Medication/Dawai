package com.example.dawaii;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Medicine;
import com.amplifyframework.datastore.generated.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendarDetails extends AppCompatActivity {
    String userName;
    User currentUser;
    List<Medicine> AvailableMed = new ArrayList<>();

    private TextView theDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_details);

        Intent dateIntent = getIntent();
        String selectedDate = dateIntent.getStringExtra("Date");
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
                                        if (date.equals(selectedDate)) {
                                            AvailableMed.add(med);
                                            break;
                                        }
                                    }
                                }
                                System.out.println("my meds " + AvailableMed);

                                handler.sendEmptyMessage(1);
                            },
                            error -> Log.e("MyAmplifyApp", "Query failure", error)
                    );
                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );
        medsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        medsRecyclerView.setAdapter(new MedAdapter(AvailableMed));

        Button backToCalendarBtn = findViewById(R.id.backToCalendarBtn);
        backToCalendarBtn.setOnClickListener(view -> {
//            Intent intent=new Intent(MainActivity.this,AddMedicineActivity.class);
            Intent intent = new Intent(CalendarDetails.this, Calendar.class);
            startActivity(intent);
        });

    }
}
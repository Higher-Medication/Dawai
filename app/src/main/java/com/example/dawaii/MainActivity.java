package com.example.dawaii;

import static java.util.concurrent.TimeUnit.SECONDS;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    boolean isSignedIn;
    String userName;

    List<User> userList = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.configure(getApplicationContext());
            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        Button getStartedBtn = findViewById(R.id.getStartedBtn);

        getStartedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToStartedActivity = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(goToStartedActivity);
            }
        });


//        Amplify.API.query(
//                ModelQuery.list(User.class),
//                response -> {
//                    for (User user : response.getData()) {
//                        userList.add(user);
//                        Log.i("MyAmplifyApp", user.getName());
//                    }
//                    List<String> datesList = userList.get(2).getMeds().get(0).getDates();
//                    List<String> timesList = userList.get(2).getMeds().get(0).getTimes();
//
//                    List intervals = new ArrayList();
//                    for (String s : datesList) {
//                        for (String s1 : timesList) {
//                            String concatinate = s + "T" + s1;
//                            LocalDateTime localDateTime = LocalDateTime.parse(concatinate);
//                            long interval = localDateTime.toEpochSecond(ZoneOffset.UTC);
//                            /// minus the now(in seconds)
//                            intervals.add(interval);
//                        }
//                    }
//                    System.out.println(intervals.toString());
////                    handler.sendEmptyMessage(1);

//                },
//                error -> Log.e("MyAmplifyApp", "Query failure", error)
//        );
//
//        List<Integer> testIntervals = new ArrayList();
//        testIntervals.add(5);
//        testIntervals.add(15);
//        testIntervals.add(30);

//        Constraints constraints = new Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
//                .setRequiresCharging(false)
//                .build();

//        for (Integer testInterval : testIntervals) {
//
//            final OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
//                    .setInitialDelay(testInterval, SECONDS)
//                    .build();
//            WorkManager.getInstance().enqueue(workRequest);
//
//        }

        Button testButton = findViewById(R.id.TestButton);
        testButton.setOnClickListener(view -> {
            Intent intent=new Intent(MainActivity.this,Calendar.class);
            startActivity(intent);
        });

    }

}


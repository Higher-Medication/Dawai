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
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Medicine;
import com.amplifyframework.datastore.generated.model.User;

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

        System.out.println("getAdapter"+profileRecyclerView.getAdapter());
        Handler newHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                profileRecyclerView.setAdapter(new ProfileAdapter(meds));

                profileRecyclerView.getAdapter().notifyDataSetChanged();
//                recyclerView();
                return false;
            }
        });
//        Amplify.Auth.fetchUserAttributes(
//                attributes -> Log.i("AuthDemo", "User attributes = " + attributes.toString()),
//                error -> Log.e("AuthDemo", "Failed to fetch user attributes.", error)
//        );

//        Amplify.Auth.fetchAuthSession(
//                result -> {
//                    AWSCognitoAuthSession cognitoAuthSession = (AWSCognitoAuthSession) result;
//                    switch(cognitoAuthSession.getIdentityId().getType()) {
//                        case SUCCESS:
//                            Log.i("AuthQuickStart", "IdentityId: " + cognitoAuthSession.getIdentityId().getValue());
//                            break;
//                        case FAILURE:
//                            Log.i("AuthQuickStart", "IdentityId not present because: " + cognitoAuthSession.getIdentityId().getError().toString());
//                    }
//                },
//                error -> Log.e("AuthQuickStart", error.toString())
//        );
//        Amplify.Auth.fetchAuthSession(
//                result -> {
//
//                    Log.i("AmplifyQuickstart", result.toString());
//                    userName = Amplify.Auth.getCurrentUser().getUserId();
//                    Amplify.API.query(
//                            ModelQuery.list(User.class, Amplify.Auth.getCurrentUser().getUsername()),
//                            response -> {
//                                Log.i("TestLogin", response.getData().getName());
////                                System.out.println("response.getData().getItems()"+ Amplify.Auth.getCurrentUser());
////                                for (User item : response.getData().getItems()) {
//                                    currentUser = response.getData();
////                                }
//                                meds = currentUser.getMeds();
//                                System.out.println("my meds " + meds);
//
//                                newHandler.sendEmptyMessage(1);
//                            },
//                            error -> Log.e("MyAmplifyApp", "Query failure", error)
//                    );
//                },
//                error -> Log.e("AmplifyQuickstart", error.toString())
//        );
//        profileRecyclerView.setLayoutManager(new LinearLayoutManager(Profile.this));
//        profileRecyclerView.setAdapter(new ProfileAdapter(meds));

        Amplify.Auth.fetchAuthSession(
                result -> {

                    Log.i("AmplifyQuickstart", result.toString());
                    userName = Amplify.Auth.getCurrentUser().getUsername();
                    Amplify.API.query(
                            ModelQuery.list(User.class, User.NAME.eq(userName)),
                            response -> {
                                Log.i("TestLogin", response.getData().toString());
//                                System.out.println("response.getData().getItems()"+ Amplify.Auth.getCurrentUser());
                                for (User item : response.getData().getItems()) {
                                currentUser = item;
                                }
                                    meds = currentUser.getMeds();
                                    System.out.println("currentUser name " + currentUser.getName());

                                    System.out.println("my meds " + meds);
                                    if(!meds.isEmpty()){
                                        System.out.println("my meds 22" + meds);

                                        newHandler.sendEmptyMessage(1);


                                    }


                            },
                            error -> Log.e("MyAmplifyApp", "Query failure", error)
                    );
                },
                error -> Log.e("AmplifyQuickstart", error.toString())
        );

        if(!meds.isEmpty()){
            System.out.println("my meds 33" + meds);

            profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            profileRecyclerView.setAdapter(new ProfileAdapter(meds));

        }




    }
    public void recyclerView (){
        RecyclerView profileRecyclerView = findViewById(R.id.profileRecyclerView);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileRecyclerView.setAdapter(new ProfileAdapter(meds));

    }


    @Override
    protected void onStart() {
        super.onStart();
//        List times=new ArrayList();
//        List dates=new ArrayList();
//        Medicine medicine = Medicine.builder()
//                .name("potato")
//                .times(times)
//                .dates(dates)
//                .expirationDate("expireDate")
//                .availableTablets(1)
//                .user(currentUser)
//                .dosage(1)
//                .build();
//        meds.add(medicine);
//        System.out.println("my meds 33" + meds);

        RecyclerView profileRecyclerView = findViewById(R.id.profileRecyclerView);
        profileRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileRecyclerView.setAdapter(new ProfileAdapter(meds));
    }
}

package com.example.dawaii;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Medicine;
import com.amplifyframework.datastore.generated.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class AddMedicineActivity extends AppCompatActivity {
    private String userName;
    private User currentUser;
    private String startDate;
    private String endDate;
    private static final String TAG = "AddMedicineActivity";
    private TextView displayStartDate;
    private TextView displayEndDate;
    private TextView expirationDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private DatePickerDialog.OnDateSetListener onDateSetListener2;
    private DatePickerDialog.OnDateSetListener onDateSetListener3;

    private TimePickerDialog.OnTimeSetListener onTimeSetListener2;
    private ArrayList<String> dosageHoursList;
    private EditText editTextTextDosageTimes;
    private Integer dosageNumberPerDay;
    boolean isAllFieldsChecked = false;
    private EditText medNameField;
    private EditText pillsCount;
    private EditText dosage;



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

        editTextTextDosageTimes = findViewById(R.id.editTextTextDosageTimes);
        editTextTextDosageTimes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(TAG, "afterTextChanged: " + editable);
                if (!editable.toString().equals("")) {
                    dosageHoursList = new ArrayList<>();
                    dosageNumberPerDay = Integer.parseInt(editable.toString());

                    for (int i = 0; i < dosageNumberPerDay; i++) {
                        Calendar cal = Calendar.getInstance();
                        int hour = cal.get(Calendar.HOUR_OF_DAY);
                        int minute = cal.get(Calendar.MINUTE);

                        TimePickerDialog dialog = new TimePickerDialog(AddMedicineActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, onTimeSetListener2, hour, minute, true);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();
                    }
                }
            }
        });

        onTimeSetListener2 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.d(TAG, "Last-onTimeSet: " + hour + ":" + minute);
                String time;
                if (hour < 10 && minute < 10) {
                    time = "0" + hour + ":" + "0" + minute + ":00";
                    dosageHoursList.add(time);
                } else if (hour < 10 && minute >= 10) {
                    time = "0" + hour + ":" + minute + ":00";
                    dosageHoursList.add(time);
                } else if (hour >= 10 && minute < 10) {
                    time = hour + ":" + "0" + minute + ":00";
                    dosageHoursList.add(time);
                } else {
                    time = hour + ":" + minute + ":00";
                    dosageHoursList.add(time);
                }
            }
        };

        startDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());
        displayStartDate = findViewById(R.id.startDateTextView);
        displayStartDate.setText(startDate);

        //StartDate
        displayStartDate.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, onDateSetListener, year, month, day);
            dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: " + year + "/" + month + '/' + day);
                startDate = month + 1 + "/" + day + "/" + year;
                displayStartDate.setText(startDate);
            }
        };

        //EndDate
        displayEndDate = findViewById(R.id.endDateText);
        displayEndDate.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH) + 7;

            DatePickerDialog dialog1 = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, onDateSetListener2, year, month, day);
            dialog1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog1.show();
        });

        onDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: " + year + "/" + month + '/' + day + "  ----EndDate");
                endDate = month + 1 + "/" + day + "/" + year;
                displayEndDate.setText(endDate);
            }
        };

        expirationDate = findViewById(R.id.expirationDate);
        expirationDate.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog1 = new DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, onDateSetListener3, year, month, day);
            dialog1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog1.show();
        });

        onDateSetListener3 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: " + year + "/" + month + '/' + day + "  ----ExpirationDate");
                String date1 = month + 1 + "/" + day + "/" + year;
                expirationDate.setText(date1);
            }
        };

        Button addMedicineButton = findViewById(R.id.addMedicineButton);
        addMedicineButton.setOnClickListener(v -> {

            medNameField = findViewById(R.id.medicineNameInput);
            String medName = medNameField.getText().toString();

            pillsCount = findViewById(R.id.pillNumberText);
            Integer pills = Integer.parseInt(String.valueOf(pillsCount.getText()));

            dosage = findViewById(R.id.tabletsTextInput);
            Integer numberOfTablets = Integer.parseInt(String.valueOf(dosage.getText()));

            TextView expirationDate = findViewById(R.id.expirationDate);
            String expireDate = expirationDate.getText().toString();

            Amplify.Auth.fetchAuthSession(
                    result -> {
                        Log.i("medicineNameInput", result.toString());
                        userName = Amplify.Auth.getCurrentUser().getUsername();

                        Amplify.API.query(
                                ModelQuery.list(User.class, User.NAME.contains(userName)),
                                response -> {
                                    Log.i("TestLogin", response.getData().toString());
                                    for (User item : response.getData().getItems()) {
                                        currentUser = item;
                                    }
                                    Medicine medicine = Medicine.builder()
                                            .name(medName)
                                            .times(dosageHoursList)
                                            .dates(getDates(startDate, endDate))
                                            .expirationDate(expireDate)
                                            .availableTablets(pills)
                                            .user(currentUser)
                                            .dosage(numberOfTablets)
                                            .build();
                                    Amplify.API.mutate(
                                            ModelMutation.create(medicine),
                                            responseto -> Log.i("MyAmplifyApp", "Added medicine with id: " + responseto.getData().getId()),
                                            error -> Log.e("MyAmplifyApp", "Create failed", error)
                                    );
                                },
                                error -> Log.e("MyAmplifyApp", "Query failure", error)
                        );
                    },
                    error -> Log.e("AmplifyQuickstart", error.toString())
            );
            List<Long> intervals = new ArrayList();
            for (String s : getDates(startDate, endDate)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate localDate = LocalDate.parse(s, formatter);
                for (String s1 : dosageHoursList) {
                    String concatinate = localDate.toString() + "T" + s1;
                    LocalDateTime localDateTime = LocalDateTime.parse(concatinate);
                    long interval = localDateTime.toEpochSecond(ZoneOffset.UTC);
                    long currentTimeInterval = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
                    intervals.add(interval - currentTimeInterval);
                }
            }
            System.out.println(intervals);
            for (Long interval : intervals) {
                if (interval > 0) {
                    Data.Builder data = new Data.Builder();
                    data.putInt("numberOfTablets", numberOfTablets);
                    data.putString("medName", medName);
                    final OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                            .setInputData(data.build())
                            .setInitialDelay(interval, TimeUnit.SECONDS)
                            .build();
                    WorkManager.getInstance().enqueue(workRequest);
                }
            }
            editTextTextDosageTimes.setText("");
            medNameField.setText("");
            pillsCount.setText("");
            dosage.setText("");
        });
    }

    private boolean CheckAllFields() {

        if (editTextTextDosageTimes.length() == 0) {
            editTextTextDosageTimes.setError("This field is required");
            return false;
        }
        System.out.println("TESSSSSSSSSSSSSSSSSST" + medNameField.getText());
        if (medNameField.length() == 0) {
            medNameField.setError("This field is required");
            return false;
        }
        if (pillsCount.length() == 0) {
            pillsCount.setError("This field is required");
            return false;
        }
        if (dosage.length() == 0) {
            dosage.setError("This field is required");
            return false;
        }
        return true;
    }

    private static List<String> getDates(String dateString1, String dateString2) {
        ArrayList<String> dates = new ArrayList<>();
        DateFormat df1 = new SimpleDateFormat("MM/dd/yyyy");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = df1.parse(dateString1);
            date2 = df1.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        while (!cal1.after(cal2)) {
            dates.add(formatter.format(cal1.getTime()));
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }

}
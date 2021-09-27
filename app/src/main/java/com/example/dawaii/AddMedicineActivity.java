package com.example.dawaii;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddMedicineActivity extends AppCompatActivity {

    private String startDate;
    private String endDate;

    private static final String TAG = "AddMedicineActivity";
    private TextView displayStartDate;
    private TextView displayEndDate;
    private TextView expirationDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private DatePickerDialog.OnDateSetListener onDateSetListener2;
    private DatePickerDialog.OnDateSetListener onDateSetListener3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicine);

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

            System.out.println("Dates " + getDates(startDate, endDate));

        });


    }


//    startDate = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(new Date());

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
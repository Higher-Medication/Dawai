package com.example.dawaii;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddMedicineActivity extends AppCompatActivity {

    private static final String TAG="AddMedicineActivity";
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

        String date_n = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());
        displayStartDate= findViewById(R.id.startDateTextView);
        displayStartDate.setText(date_n);

        //StartDate

        displayStartDate.setOnClickListener(view -> {
            Calendar cal=Calendar.getInstance();
            int year=cal.get(Calendar.YEAR);
            int month=cal.get(Calendar.MONTH);
            int day=cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog=new DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog,onDateSetListener,year,month,day);
            dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

        });

        onDateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: "+year + "/" + month+ '/' +day);
                String date=month+1 + "/" + day+ "/" +year;
                displayStartDate.setText(date);
            }
        };


        //EndDate

        displayEndDate=findViewById(R.id.endDateText);

        displayEndDate.setOnClickListener(view -> {
            Calendar cal=Calendar.getInstance();
            int year=cal.get(Calendar.YEAR);
            int month=cal.get(Calendar.MONTH);
            int day=cal.get(Calendar.DAY_OF_MONTH)+7;

            DatePickerDialog dialog1=new DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog,onDateSetListener2,year,month,day);
            dialog1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog1.show();

        });

        onDateSetListener2= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: "+year + "/" + month+ '/' +day+"  ----EndDate");
                String date1=month+1 + "/" + day+ "/" +year;
                displayEndDate.setText(date1);
            }
        };

        expirationDate=findViewById(R.id.expirationDate);

        expirationDate.setOnClickListener(view -> {
            Calendar cal=Calendar.getInstance();
            int year=cal.get(Calendar.YEAR);
            int month=cal.get(Calendar.MONTH);
            int day=cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog1=new DatePickerDialog(this,android.R.style.Theme_Holo_Light_Dialog,onDateSetListener3,year,month,day);
            dialog1.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog1.show();

        });

        onDateSetListener3= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "onDateSet: "+year + "/" + month+ '/' +day+"  ----ExpirationDate");
                String date1=month+1 + "/" + day+ "/" +year;
                expirationDate.setText(date1);
            }
        };



    }
}
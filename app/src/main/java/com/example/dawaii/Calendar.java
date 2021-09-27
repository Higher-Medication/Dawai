package com.example.dawaii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Calendar extends AppCompatActivity {
    CalendarView calendar;
    TextView date_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        FloatingActionButton addMedicine = findViewById(R.id.addMedicineBtn);

        addMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToStartedActivity = new Intent(Calendar.this, AddMedicineActivity.class);
                startActivity(goToStartedActivity);
            }
        });

        calendar = (CalendarView) findViewById(R.id.calendar);
//        date_view = (TextView) findViewById(R.id.date_view);

        // Add Listener in calendar
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String Date = dayOfMonth + "-" + (month + 1) + "-" + year;

                // set this date in TextView for Display
//                date_view.setText(Date);

                Intent intent = new Intent();
                intent.setClass(Calendar.this,CalendarDetails.class);
                intent.putExtra("Date",Date);
                Calendar.this.startActivity(intent);
            }
        });


    }
}
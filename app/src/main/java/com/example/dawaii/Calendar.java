package com.example.dawaii;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

public class Calendar extends AppCompatActivity {
    CalendarView calendar;
    TextView date_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        calendar = (CalendarView) findViewById(R.id.calendar);
        date_view = (TextView) findViewById(R.id.date_view);

        // Add Listener in calendar
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String Date = dayOfMonth + "-" + (month + 1) + "-" + year;

                // set this date in TextView for Display
                date_view.setText(Date);

                Intent intent = new Intent();
                intent.setClass(Calendar.this,CalendarDetails.class);
                intent.putExtra("Date",Date);
                Calendar.this.startActivity(intent);
            }
        });


    }
}
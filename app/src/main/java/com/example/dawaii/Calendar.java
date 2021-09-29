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


        Button addMedicine = findViewById(R.id.addMedicineBtn);

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
                String Date;
                if (month<9&& dayOfMonth<10){
                     Date = "0"+(month + 1) +"/" +"0"+dayOfMonth  + "/" + year;
                }else if (month<9&& dayOfMonth>=10) {
                     Date = "0"+(month + 1) +"/" +dayOfMonth  + "/" + year;
                }else if (month>=9&& dayOfMonth<10){
                    Date = (month + 1) +"/" +"0"+dayOfMonth  + "/" + year;
                }else{
                    Date = (month + 1) +"/" +dayOfMonth  + "/" + year;
                }

                Intent intent = new Intent();
                intent.setClass(Calendar.this,CalendarDetails.class);
                intent.putExtra("Date",Date);
                Calendar.this.startActivity(intent);
            }
        });


    }
}
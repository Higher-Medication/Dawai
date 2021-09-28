package com.example.dawaii;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CalendarDetails extends AppCompatActivity {

    private  TextView theDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_details);

        theDate = (TextView) findViewById(R.id.dateText);

        Intent dateIntent = getIntent();
        String date = dateIntent.getStringExtra("Date");
        theDate.setText(date);
    }
}
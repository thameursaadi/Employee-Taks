package com.example.thamersaadi.miniprojet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

/**
 * Created by thamersaadi on 02/04/2018.
 */

public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";

    private CalendarView ca;
     @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        ca=(CalendarView)findViewById(R.id.calendarView);

        ca.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date =dayOfMonth+"/"+month+"/"+year;
                Log.d(TAG, "onSelectedDayChange: date: "+date);
                Intent intent = new Intent(CalendarActivity.this, AjouterEmployee.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
    }
}

package com.lifemanagementapp.lifemanagementapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.*;
import android.view.*;
import android.widget.*;
import com.lifemanagementapp.*;
import android.widget.CalendarView.*;
import android.widget.Toast;



public class Calender extends ActionBarActivity {

    private static final long double_Click_Time = 250; // in millis
    private long lastPressTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        final CalendarView calendarView=(CalendarView)findViewById(R.id.action_calendar);
        calendarView.setClickable(true);
        ExpandableListView mExpandableListView = (ExpandableListView)findViewById(R.id.action_calendar_event);
        //mExpandableListView.expandGroup(groupPosition);
        calendarView.setOnDateChangeListener(new OnDateChangeListener(){ // Event add to listen for date click
            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                long pressTime = System.currentTimeMillis();
                if (pressTime - lastPressTime <= double_Click_Time) { // checks the time between last click and current click
                    String message = "Double Click";
                    Toast.makeText(getApplicationContext(), message,
                            Toast.LENGTH_LONG).show();
                } else {
                    String message = String.valueOf(month + 1) + "-" + String.valueOf(dayOfMonth) + "-" + String.valueOf(year);
                    Toast.makeText(getApplicationContext(), message,
                            Toast.LENGTH_LONG).show();
                }
                lastPressTime = pressTime;
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calender, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}

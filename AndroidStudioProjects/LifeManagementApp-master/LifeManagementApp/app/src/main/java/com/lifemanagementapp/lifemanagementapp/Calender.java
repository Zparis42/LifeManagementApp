package com.lifemanagementapp.lifemanagementapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.*;
import android.view.*;
import android.widget.*;
import com.lifemanagementapp.*;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import android.widget.CalendarView.*;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class Calender extends ActionBarActivity {

    private static final long double_Click_Time = 250; // in millis
    private long lastPressTime;
    private String selectedDate;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;
    Date currentDate;
    Button addEvent;
    EditText textfield;
    CaldroidFragment caldroidFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        /*Get elements of views*/
        final RelativeLayout overlay = (RelativeLayout)findViewById(R.id.overlay_menu);
        final CalendarView calendarView=(CalendarView)findViewById(R.id.action_calendar);
        textfield  = (EditText)findViewById(R.id.editText);
        addEvent = (Button)findViewById(R.id.overlay_button);
        //ExpandableListView mExpandableListView = (ExpandableListView)findViewById(R.id.action_calendar_event);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        /*End get elements stage*/
        caldroidFragment = new CaldroidFragment();
        /* Listener Begin*/
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
                Toast.makeText(getApplicationContext(), "Date Select",
                        Toast.LENGTH_SHORT).show();
                currentDate = date;
            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;

            }

            @Override
            public void onLongClickDate(Date date, View view) {
                Toast.makeText(getApplicationContext(),
                        "Long click " ,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                Toast.makeText(getApplicationContext(),
                        "Caldroid view is created",
                        Toast.LENGTH_SHORT).show();
            }

        };
        /* Listener end*/
        overlay.setVisibility(View.INVISIBLE); //set over lay to invisible
        calendarView.setClickable(true);

        caldroidFragment.setCaldroidListener(listener);
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);

        caldroidFragment.setArguments(args);

        android.support.v4.app.FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.action_calendar, caldroidFragment);
        t.commit();
        Button caladdEvent = (Button)findViewById(R.id.cal_add_event);
        caladdEvent.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        overlay.setVisibility(View.VISIBLE);
                    }
                });
        //mExpandableListView.expandGroup(groupPosition);
        /*
        calendarView.setOnDateChangeListener(new OnDateChangeListener(){ // Event add to listen for date click
            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {
                long pressTime = System.currentTimeMillis();
                String message;
                String currentSelect = String.valueOf(month + 1) + "-" + String.valueOf(dayOfMonth) + "-" + String.valueOf(year);
                if (pressTime - lastPressTime <= double_Click_Time) { // checks the time between last click and current click
                    //setContentView(R.layout.overlayview);
                    overlay.setVisibility(View.VISIBLE);
                    message = Long.toString(calendarView.getDate());
                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                } else {
                    message = sharedPref.getString(currentSelect+"Event","Not There");//get value from Key "Event"
                    selectedDate = currentSelect;
                    //message = String.valueOf(month + 1) + "-" + String.valueOf(dayOfMonth) + "-" + String.valueOf(year);
                    Toast.makeText(getApplicationContext(), message,
                            Toast.LENGTH_LONG).show();
                }
                lastPressTime = pressTime;
            }

        });*/
        addEvent.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        editor.putString(selectedDate+"Event", textfield.getText().toString());
                        editor.commit();
                        addEvent(currentDate,caldroidFragment);
                        overlay.setVisibility(View.INVISIBLE); //set over lay to invisible
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
    public void onClick(View v) {

        //Your Logic
    }
    public void addEvent(Date date,CaldroidFragment fragment)
    {
        fragment.setBackgroundDrawableForDate(ResourcesCompat.getDrawable(getResources(), R.drawable.calendar_next_arrow, null),date);
        fragment.refreshView();
    }
}

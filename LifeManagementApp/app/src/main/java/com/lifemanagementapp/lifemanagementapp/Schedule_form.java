// Zac's event form class
package com.lifemanagementapp.lifemanagementapp;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;

public class Schedule_form extends AppCompatActivity implements TimePickerFragment.OnFragmentInteractionListener, DatePickerFragment.DateFragmentInteractionListener {

    Button timeButton;
    Button dateButton;

    // Variables for time
    final Calendar c = Calendar.getInstance();
    int setHour = c.get(Calendar.HOUR_OF_DAY);
    int setMinute = c.get(Calendar.MINUTE);
    String ampm = "AM";

    // Variables for date
    int setYear = c.get(Calendar.YEAR);
    int setMonth = c.get(Calendar.MONTH);
    int setDay = c.get(Calendar.DAY_OF_MONTH);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_form);

        // Set the text of the time button to be the current time
        // Fix AM/PM
        ampm = "AM";
        if (setHour > 12) {
            setHour -= 12;
            ampm = "PM";
        } else if (setHour == 0) {
            setHour = 12;
        } else if (setHour == 12) {
            ampm = "PM";
        }
        // Add a 0 if the minutes are single digit
        String offset = "";
        if (setMinute < 10) {
            offset = "0";
        }
        timeButton = (Button) findViewById(R.id.timeButton);
        String temp = setHour + ":" + offset + setMinute + " " + ampm;
        timeButton.setText(temp);

        // Set the text of the date button to be the current date
        dateButton = (Button) findViewById(R.id.dateButton);
        temp = setMonth + " " + setDay + ", " + setYear;
        dateButton.setText(temp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule_form, menu);
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

    // When the "set time" button is pressed, this method brings up the time picker
    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    // When the time is set in the picker, this method will be called
    public void onFragmentInteraction(int hour, int minute) {
        // TODO: Add method body

        // Update the button text
        ampm = "AM";
        setHour = hour;
        setMinute = minute;
        if (setHour > 12) {
            setHour -= 12;
            ampm = "PM";
        } else if (setHour == 0) {
            setHour = 12;
        } else if (setHour == 12) {
            ampm = "PM";
        }
        // Add a 0 if the minutes are single digit
        String offset = "";
        if (setMinute < 10) {
            offset = "0";
        }
        String temp = setHour + ":" + offset + setMinute + " " + ampm;
        timeButton.setText(temp);
    }

    // When the date is set in the picker, this method will be called
    @Override
    public void DateFragmentInteraction(int year, int month, int day) {
        setYear = year;
        setMonth = month;
        setDay = day;
        // Set the text of the date button to be the current date
        dateButton = (Button) findViewById(R.id.dateButton);
        String temp = setMonth + " " + setDay + ", " + setYear;
        dateButton.setText(temp);
    }
}

// Zac's event form class
package com.lifemanagementapp.lifemanagementapp;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;



public class Schedule_form extends AppCompatActivity implements TimePickerFragment.TimeFragmentInteractionListener, DatePickerFragment.DateFragmentInteractionListener {

    Button timeButton;
    Button dateButton;
    Button timeEndButton;
    Button dateEndButton;

    private static final int START = 0;
    private static final int END = 1;

    // Variable for what button we're pressing
    int state;

    // Variable for event name
    String eventName = null;

    // Variables for time
    final Calendar c = Calendar.getInstance();
    int setHour = c.get(Calendar.HOUR_OF_DAY);
    int setMinute = c.get(Calendar.MINUTE);
    String ampm = "AM";

    // Variables for end time
    int setEndHour = c.get(Calendar.HOUR_OF_DAY);
    int setEndMinute = c.get(Calendar.MINUTE);
    String ampmEnd = "AM";

    // Variables for date
    int setYear = c.get(Calendar.YEAR);
    int setMonth = c.get(Calendar.MONTH);
    int setDay = c.get(Calendar.DAY_OF_MONTH);

    // Variables for end date
    int setYearEnd = c.get(Calendar.YEAR);
    int setMonthEnd = c.get(Calendar.MONTH);
    int setDayEnd = c.get(Calendar.DAY_OF_MONTH);

    // Database shenanigans

    // Put everything into the db on submit
    public void formSubmit(View v){

        EventDataDbHelper edHelper = new EventDataDbHelper(getApplicationContext());

        // Store the name
        final EditText edit = (EditText) findViewById(R.id.eventName);
        eventName = (String) edit.getText().toString();
        // Get database in writeable mode
        SQLiteDatabase db = edHelper.getWritableDatabase();

        // Create new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(EventDataContract.EventEntry.COLUMN_NAME_EVENT_NAME, eventName);
        values.put(EventDataContract.EventEntry.COLUMN_NAME_START_YEAR, setYear);
        values.put(EventDataContract.EventEntry.COLUMN_NAME_START_MONTH, setMonth);
        values.put(EventDataContract.EventEntry.COLUMN_NAME_START_DAY, setDay);
        values.put(EventDataContract.EventEntry.COLUMN_NAME_START_HOUR, setHour);
        values.put(EventDataContract.EventEntry.COLUMN_NAME_START_MINUTE, setMinute);
        values.put(EventDataContract.EventEntry.COLUMN_NAME_END_YEAR, setYearEnd);
        values.put(EventDataContract.EventEntry.COLUMN_NAME_END_MONTH, setMonthEnd);
        values.put(EventDataContract.EventEntry.COLUMN_NAME_END_DAY, setDayEnd);
        values.put(EventDataContract.EventEntry.COLUMN_NAME_END_HOUR, setEndHour);
        values.put(EventDataContract.EventEntry.COLUMN_NAME_END_MINUTE, setEndMinute);

        long newRowID = db.insert(EventDataContract.EventEntry.TABLE_NAME, null, values);


        /*
        // Test code to see if writing and reading work
        db = edHelper.getReadableDatabase();
        // Projection of what information will be used
        String[] projection = {
                EventDataContract.EventEntry.COLUMN_NAME_EVENT_NAME,
                EventDataContract.EventEntry.COLUMN_NAME_START_YEAR,
                EventDataContract.EventEntry.COLUMN_NAME_START_MONTH,
                EventDataContract.EventEntry.COLUMN_NAME_START_DAY,
                EventDataContract.EventEntry.COLUMN_NAME_START_HOUR,
                EventDataContract.EventEntry.COLUMN_NAME_START_MINUTE,
                EventDataContract.EventEntry.COLUMN_NAME_END_YEAR,
                EventDataContract.EventEntry.COLUMN_NAME_END_MONTH,
                EventDataContract.EventEntry.COLUMN_NAME_END_DAY,
                EventDataContract.EventEntry.COLUMN_NAME_END_HOUR,
                EventDataContract.EventEntry.COLUMN_NAME_END_MINUTE
        };

        // Filter results WHERE "eventDay" = whatever day was entered, for testing
        String selection = EventDataContract.EventEntry.COLUMN_NAME_START_DAY + " = ?";
        Integer setDayPrime = setDay;
        String[] selectionArgs = { setDayPrime.toString() };

        // How results should be sorted
        String sortOrder = EventDataContract.EventEntry.COLUMN_NAME_START_DAY + " DESC";

        Cursor cursor = db.query(
                EventDataContract.EventEntry.TABLE_NAME,        // The table to query
                projection,                                     // The columns to return
                selection,                                      // The columns for the WHERE clause
                selectionArgs,                                  // The values for the WHERE clause
                null,                                           // don't group the rows
                null,                                           // don't filter by row groups
                sortOrder                                       // The sort order
        );

        if (!cursor.moveToNext()) {
            cursor.close();
            return;
        }
        System.out.println(cursor.getString(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_EVENT_NAME)));
        System.out.println(cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_START_YEAR)));
        System.out.println(cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_START_MONTH)));
        System.out.println(cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_START_DAY)));
        System.out.println(cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_START_HOUR)));
        System.out.println(cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_START_MINUTE)));
        System.out.println(cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_END_YEAR)));
        System.out.println(cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_END_MONTH)));
        System.out.println(cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_END_DAY)));
        System.out.println(cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_END_HOUR)));
        System.out.println(cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_END_MINUTE)));
        cursor.close();
        */
        finish();
    }


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
        timeEndButton = (Button) findViewById(R.id.timeEndButton);
        String temp = setHour + ":" + offset + setMinute + " " + ampm;
        timeButton.setText(temp);
        timeEndButton.setText(temp);

        // Set the text of the date buttons to be the current date
        String strMonth = "";
        strMonth = formatMonth(setMonth);


        dateButton = (Button) findViewById(R.id.dateButton);
        dateEndButton = (Button) findViewById(R.id.dateEndButton);
        temp = strMonth + " " + setDay + ", " + setYear;
        dateButton.setText(temp);
        dateEndButton.setText(temp);
    }

    // Format the month based on an int from the picker
    public String formatMonth(int setMonth){
        String strMonth = "";
        if (setMonth == 0) {
            strMonth = "January";
        } else if (setMonth == 1) {
            strMonth = "February";
        } else if (setMonth == 2) {
            strMonth = "March";
        } else if (setMonth == 3) {
            strMonth = "April";
        } else if (setMonth == 4) {
            strMonth = "May";
        } else if (setMonth == 5) {
            strMonth = "June";
        } else if (setMonth == 6) {
            strMonth = "July";
        } else if (setMonth == 7) {
            strMonth = "August";
        } else if (setMonth == 8) {
            strMonth = "September";
        } else if (setMonth == 9) {
            strMonth = "October";
        } else if (setMonth == 10) {
            strMonth = "November";
        } else if (setMonth == 11) {
            strMonth = "December";
        }
        return strMonth;
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

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    // When the "set time" button is pressed, this method brings up the time picker
    public void showTimePickerDialog(View v) {
        state = START;
        DialogFragment newFragment = TimePickerFragment.newInstance(setHour, setMinute);//new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        state = START;
        DialogFragment newFragment = DatePickerFragment.newInstance(setYear, setMonth, setDay);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimeEndPickerDialog(View v) {
        state = END;
        DialogFragment newFragment = TimePickerFragment.newInstance(setEndHour, setEndMinute);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void showDateEndPickerDialog(View v) {
        state = END;
        DialogFragment newFragment = DatePickerFragment.newInstance(setYearEnd, setMonthEnd, setDayEnd);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    // When the time is set in the picker, this method will be called
    public void TimeFragmentInteraction(int hour, int minute) {

        // Update the button text
        if (state == START) {
            ampm = "AM";
            setHour = hour;
            setMinute = minute;
            int displayHour = setHour;
            if (setHour > 12) {
                displayHour = setHour - 12;
                ampm = "PM";
            } else if (setHour == 0) {
                displayHour = 12;
            } else if (setHour == 12) {
                ampm = "PM";
            }
            // Add a 0 if the minutes are single digit
            String offset = "";
            if (setMinute < 10) {
                offset = "0";
            }
            String temp = displayHour + ":" + offset + setMinute + " " + ampm;
            timeButton.setText(temp);
        } else {
            ampm = "AM";
            setEndHour = hour;
            setEndMinute = minute;
            int displayHour = setEndHour;
            if (setEndHour > 12) {
                displayHour = setEndHour - 12;
                ampm = "PM";
            } else if (setEndHour == 0) {
                displayHour = 12;
            } else if (setEndHour == 12) {
                ampm = "PM";
            }
            // Add a 0 if the minutes are single digit
            String offset = "";
            if (setEndMinute < 10) {
                offset = "0";
            }
            String temp = displayHour + ":" + offset + setEndMinute + " " + ampm;
            timeEndButton.setText(temp);
        }

        // If the end date/time is earlier than the start date/time, fix that
        // to avoid potential temporal dissonance
        fixTime();
    }

    // When the date is set in the picker, this method will be called
    @Override
    public void DateFragmentInteraction(int year, int month, int day) {
        if (state == START) {
            setYear = year;
            setMonth = month;
            setDay = day;

            // Set the text of the date button to be the current date
            String strMonth = "";
            strMonth = formatMonth(setMonth);

            dateButton = (Button) findViewById(R.id.dateButton);
            String temp = strMonth + " " + setDay + ", " + setYear;
            dateButton.setText(temp);
        } else {
            setYearEnd = year;
            setMonthEnd = month;
            setDayEnd = day;

            // Set the text of the date button to be the current date
            String strMonth = "";
            strMonth = formatMonth(setMonthEnd);

            String temp = strMonth + " " + setDayEnd + ", " + setYearEnd;
            dateEndButton.setText(temp);
        }

        // If the end date/time is earlier than the start date/time, fix that
        // to avoid potential temporal dissonance
        fixTime();

    }

    private void fixTime() {
        // Compare the dates/times of the two
        int start = setMinute + 100 * setHour + 10000 * setDay + 1000000 * setMonth + 100000000 * setYear;
        int end = setEndMinute + 100 * setEndHour + 10000 * setDayEnd + 1000000 * setMonthEnd + 100000000 * setYearEnd;
        // If the end is before the start, set it to be equal to the start
        if (end < start) {
            setYearEnd = setYear;
            setMonthEnd = setMonth;
            setDayEnd = setDay;
            setEndHour = setHour;
            setEndMinute = setMinute;

            // Update the end buttons accordingly
            // Update the time button text
            ampm = "AM";
            int displayHour = setEndHour;
            if (setEndHour > 12) {
                displayHour = setEndHour - 12;
                ampm = "PM";
            } else if (setEndHour == 0) {
                displayHour = 12;
            } else if (setEndHour == 12) {
                ampm = "PM";
            }
            // Add a 0 if the minutes are single digit
            String offset = "";
            if (setEndMinute < 10) {
                offset = "0";
            }
            String temp = displayHour + ":" + offset + setEndMinute + " " + ampm;
            timeEndButton.setText(temp);

            // Update the date button text
            String strMonth = "";
            strMonth = formatMonth(setMonthEnd);

            temp = strMonth + " " + setDayEnd + ", " + setYearEnd;
            dateEndButton.setText(temp);

        }
    }
}

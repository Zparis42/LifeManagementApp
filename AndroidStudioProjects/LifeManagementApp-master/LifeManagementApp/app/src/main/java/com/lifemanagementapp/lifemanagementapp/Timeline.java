package com.lifemanagementapp.lifemanagementapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.style.TtsSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;


public class Timeline extends ActionBarActivity {

    EventDataDbHelper edHelper;
    SQLiteDatabase db;
    Cursor cursor;
    ListView listView;
    public static ArrayList<String> list = new ArrayList<>();
    public static ArrayAdapter<String> timelineAdapter;
    int Day;
    int Month;
    int Year;
    String[] Months = {"January","February","March","April","May","June","July","August","September","October","November", "December"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        listView = (ListView)findViewById(R.id.time_line_display);
        //ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listView);
       // listView.setAdapter(myarrayAdapter);
        //listView.setTextFilterEnabled(true);
        int []Date = getIntent().getIntArrayExtra("Date");
        Month = Date[0];
        Day = Date[1];
        Year = Date[2];

        Toolbar toolbar = (Toolbar) findViewById( R.id.my_toolbar );
        setSupportActionBar(toolbar) ;
        String title = Months[Month] + "-" + Integer.toString(Day)+"-"+Integer.toString(Year);
        getSupportActionBar().setTitle(title);
        timelineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(timelineAdapter);
        listView.setTextFilterEnabled(true);
        getEvents(Month,Day,Year);
        /***************Data Base*****************/

        /******************Data Base End**********************/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
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
    public void getEvents(int Month,int Day,int Year)
    {
        edHelper = new EventDataDbHelper(getApplicationContext());
        db = edHelper.getReadableDatabase();

        // Projection of what information will be used
        String[] projection = {
                EventDataContract.EventEntry._ID,
                EventDataContract.EventEntry.COLUMN_NAME_EVENT_NAME,
                /*EventDataContract.EventEntry.COLUMN_NAME_START_YEAR,
                EventDataContract.EventEntry.COLUMN_NAME_START_MONTH,*/
                //EventDataContract.EventEntry.COLUMN_NAME_START_DAY
                EventDataContract.EventEntry.COLUMN_NAME_START_HOUR,
                EventDataContract.EventEntry.COLUMN_NAME_START_MINUTE,
                //EventDataContract.EventEntry.COLUMN_NAME_END_YEAR,
                //EventDataContract.EventEntry.COLUMN_NAME_END_MONTH,
                //EventDataContract.EventEntry.COLUMN_NAME_END_DAY,
                EventDataContract.EventEntry.COLUMN_NAME_END_HOUR,
                EventDataContract.EventEntry.COLUMN_NAME_END_MINUTE
        };

        // Filter results WHERE "eventDay" = whatever day was entered, for testing
        String selection = EventDataContract.EventEntry.COLUMN_NAME_START_YEAR + " = ?" + " and " +
                EventDataContract.EventEntry.COLUMN_NAME_START_MONTH + " = ?" + " and " + EventDataContract.EventEntry.COLUMN_NAME_START_DAY + " = ?";
        Integer checkYear = Year;
        Integer checkMonth = Month;
        Integer checkDay = Day;

        String[] selectionArgs = { checkYear.toString(), checkMonth.toString(), checkDay.toString() };
        // How results should be sorted
        String sortOrder = EventDataContract.EventEntry.COLUMN_NAME_START_HOUR + " ASC";

        cursor = db.query(
                EventDataContract.EventEntry.TABLE_NAME,        // The table to query
                projection,                                     // The columns to return
                selection,                                      // The columns for the WHERE clause
                selectionArgs,                                  // The values for the WHERE clause
                null,                                           // don't group the rows
                null,                                           // don't filter by row groups
                sortOrder                                       // The sort order
        );
        int count = 0;
        while (cursor.moveToNext()){
            // Every date in dateBools that is true has an event for that day
            String event = cursor.getString(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_EVENT_NAME));
            int startTime = cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_START_HOUR));
            int startMin = cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_START_MINUTE));
            int endTime = cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_END_HOUR));
            int endMin = cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_END_MINUTE));
            String timeofDay1 = "AM";
            String timeofDay2 = "AM";

            if(startTime > 12)
            {
                if(startTime!=24) {
                    timeofDay1 = "PM";
                }
                startTime = startTime - 12;
            }
            if(endTime > 12)
            {
                if(endTime!=24) {
                    timeofDay2 = "PM";
                }
                endTime = endTime - 12;
            }
            String displayText = formatTimes(event,startTime,startMin,endTime,endMin,timeofDay1,timeofDay2);
            list.add(displayText);
            timelineAdapter.notifyDataSetChanged();
            count++;
        }
        System.out.print(count);
        if(count == 0)
        {
            String displayText = "There are no events for this day.";
            list.add(displayText);
            timelineAdapter.notifyDataSetChanged();
        }
        //timelineAdapter.notifyDataSetChanged();
    }
    public String formatTimes(String EventName,int StartHour,int StartMinute, int EndHour,int EndMinute,String timeOfDay1,String timeOfDay2)
    {
        String Min1 = Integer.toString(StartMinute);
        String Min2 = Integer.toString(EndMinute);
        if(StartMinute < 10)
        {
            Min1 = '0'+Min1;
        }
        if(EndMinute < 10)
        {
            Min2 = '0'+Min2;
        }
        String displayString = "Event: " + EventName + "\n"
            + "Start Time: " + Integer.toString(StartHour) +":"+Min1 + timeOfDay1+"-"
            + "End Time: " + Integer.toString(EndHour)+":"+Min2+timeOfDay2;
        return displayString;
    }
    public void addToView(String display)
    {

    }
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        list = new ArrayList<>();
        timelineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(timelineAdapter);
        listView.setTextFilterEnabled(true);
        getEvents(Month,Day,Year);
    }
}

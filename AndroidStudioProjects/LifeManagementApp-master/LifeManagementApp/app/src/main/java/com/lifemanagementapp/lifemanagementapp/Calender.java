package com.lifemanagementapp.lifemanagementapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ScrollingView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.*;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import com.lifemanagementapp.*;
import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import android.widget.CalendarView.*;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    final Calendar c = Calendar.getInstance();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;

    EventDataDbHelper edHelper;
    SQLiteDatabase db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        caldroidFragment = new CaldroidFragment();
        /*******************  Navigation View Section**************/
        Toolbar toolbar = (Toolbar) findViewById( R.id.my_toolbar );
        setSupportActionBar(toolbar) ;

        mNavigationView = (NavigationView) findViewById( R.id.nav_view );
        mDrawerLayout = (DrawerLayout) findViewById( R.id.nav_drawer );
        mDrawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close );
        mDrawerLayout.setDrawerListener( mDrawerToggle );
        mDrawerToggle.syncState( );

        // Removes tint from menu items
        mNavigationView.setItemIconTintList(null);

        // Enables the navigation button on the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /******************* Navigation View End*******************/

        /*Get elements of views*/

        //final CalendarView calendarView=(CalendarView)findViewById(R.id.action_calendar);
        /***************************************************************************************/
        //DataBase Variables
        edHelper = new EventDataDbHelper(getApplicationContext());
        db = edHelper.getReadableDatabase();

        // Projection of what information will be used
        String[] projection = {
                EventDataContract.EventEntry._ID,
                /*EventDataContract.EventEntry.COLUMN_NAME_EVENT_NAME,
                EventDataContract.EventEntry.COLUMN_NAME_START_YEAR,
                EventDataContract.EventEntry.COLUMN_NAME_START_MONTH,*/
                EventDataContract.EventEntry.COLUMN_NAME_START_DAY
                /*EventDataContract.EventEntry.COLUMN_NAME_START_HOUR,
                EventDataContract.EventEntry.COLUMN_NAME_START_MINUTE,
                EventDataContract.EventEntry.COLUMN_NAME_END_YEAR,
                EventDataContract.EventEntry.COLUMN_NAME_END_MONTH,
                EventDataContract.EventEntry.COLUMN_NAME_END_DAY,
                EventDataContract.EventEntry.COLUMN_NAME_END_HOUR,
                EventDataContract.EventEntry.COLUMN_NAME_END_MINUTE*/
        };

        // Filter results WHERE "eventDay" = whatever day was entered, for testing
        String selection = EventDataContract.EventEntry.COLUMN_NAME_START_YEAR + " = ?" + " and " +
                EventDataContract.EventEntry.COLUMN_NAME_START_MONTH + " = ?";
        Integer checkYear = c.get(Calendar.YEAR);
        Integer checkMonth = c.get(Calendar.MONTH);

        String[] selectionArgs = { checkYear.toString(), checkMonth.toString() };
        // How results should be sorted
        String sortOrder = EventDataContract.EventEntry.COLUMN_NAME_START_DAY + " ASC";

        cursor = db.query(
                EventDataContract.EventEntry.TABLE_NAME,        // The table to query
                projection,                                     // The columns to return
                selection,                                      // The columns for the WHERE clause
                selectionArgs,                                  // The values for the WHERE clause
                null,                                           // don't group the rows
                null,                                           // don't filter by row groups
                sortOrder                                       // The sort order
        );

        boolean[] dateBools = new boolean[31];
        for (int x = 0; x < 31; x++){
            dateBools[x] = false;
        }
        while (cursor.moveToNext()){
            // Every date in dateBools that is true has an event for that day
            dateBools[cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_START_DAY))] = true;
        }
        for(int x = 0;x < 31;x++)
        {
            if(dateBools[x] == true)
            {
                Date newDate = new Date(checkYear-1900,checkMonth,x);
                addEvent(newDate,caldroidFragment);
            }
        }
        /***************************************************************************************/
        //ExpandableListView mExpandableListView = (ExpandableListView)findViewById(R.id.action_calendar_event);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        /*End get elements stage*/

        /* Listener Begin*/
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {

                Intent e = new Intent( Calender.this, Timeline.class ); // Change "test_activity.class" to whatever class this should link to
                int [] SendDate = {date.getMonth(),date.getDate(),date.getYear()+1900};
                e.putExtra("Date",SendDate);
                startActivity(e);

            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
                // Projection of what information will be used
                String[] projection = {
                        EventDataContract.EventEntry._ID,
                        EventDataContract.EventEntry.COLUMN_NAME_START_DAY
                };

                // Filter results WHERE "eventDay" = whatever day was entered, for testing
                String selection = EventDataContract.EventEntry.COLUMN_NAME_START_YEAR + " = ?" + " and " +
                        EventDataContract.EventEntry.COLUMN_NAME_START_MONTH + " = ?";
                Integer checkYear = year;
                Integer checkMonth = month;

                String[] selectionArgs = { checkYear.toString(), checkMonth.toString() };


                // How results should be sorted
                String sortOrder = EventDataContract.EventEntry.COLUMN_NAME_START_DAY + " ASC";

                cursor = db.query(
                        EventDataContract.EventEntry.TABLE_NAME,        // The table to query
                        projection,                                     // The columns to return
                        selection,                                      // The columns for the WHERE clause
                        selectionArgs,                                  // The values for the WHERE clause
                        null,                                           // don't group the rows
                        null,                                           // don't filter by row groups
                        sortOrder                                       // The sort order
                );

                boolean[] dateBools = new boolean[31];
                for (int x = 0; x < 31; x++){
                    dateBools[x] = false;
                }
                while (cursor.moveToNext()){
                    // Every date in dateBools that is true has an event for that day
                    dateBools[cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_START_DAY))] = true;
                }
                for(int x = 0;x < 31;x++)
                {
                    if(dateBools[x] == true)
                    {
                        Date newDate = new Date(year-1900,month,x);
                        addEvent(newDate,caldroidFragment);
                    }
                }
            }

            @Override
            public void onLongClickDate(Date date, View view) {
            }

            @Override
            public void onCaldroidViewCreated() {

            }

        };
        /* Listener end*/

        //calendarView.setClickable(true);

        caldroidFragment.setCaldroidListener(listener);
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);

        caldroidFragment.setArguments(args);
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        // Get the current date
        caldroidFragment.getMonth();
        int currYear = caldroidFragment.getYear();        // Year as 4-digit int
        int currMonth = caldroidFragment.getMonth()+1;      // Month as int (0 - 11)
        int currDay = c.get(Calendar.DAY_OF_MONTH); // Day as int (1-31)
        String dateObj = Integer.toString(currYear)+Integer.toString(currMonth)+Integer.toString(currDay);
        try {
            Date getCurrDate = df.parse(dateObj);
            caldroidFragment.setSelectedDate(getCurrDate);
            currentDate = getCurrDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.addToBackStack("caldroid");
        t.replace(R.id.action_calendar, caldroidFragment);
        t.commit();
        Button caladdEvent = (Button)findViewById(R.id.cal_add_event);
        caladdEvent.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        Intent f = new Intent( Calender.this, Schedule_form.class ); // Change "test_activity.class" to whatever class this should link to
                        startActivity( f );
                        //overlay.setVisibility(View.VISIBLE);
                    }
                });

        /*addEvent.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        editor.putString(selectedDate+"Event", textfield.getText().toString());
                        editor.commit();
                        addEvent(currentDate,caldroidFragment);
                        overlay.setVisibility(View.INVISIBLE); //set over lay to invisible
                    }
                });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homescreen, menu);
        return true;
    }

///////////////////////////////////////////////POTENTIAL PROBLEM/////////////////////
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
/////////////////////////////////////////////////////////////////////////////////////
    public void onClick(View v) {

        //Your Logic
    }
    public void addEvent(Date date,CaldroidFragment fragment)
    {
        fragment.setBackgroundDrawableForDate(ResourcesCompat.getDrawable(getResources(), R.drawable.green_square_tab, null),date);
        fragment.refreshView();
    }
    public void displayVoid()
    {

    }
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        else {
            return true;
        }
    }

    // Starts Activity based on whatever menu item is selected from navigation drawer
    public void onNavMenuItemClick( MenuItem item ) {

        switch ( item.getItemId( ) ) {
            case R.id.action_calendar:
                Intent a = new Intent( Calender.this, Calender.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( a );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_health:
                Intent b = new Intent( Calender.this, test_activity.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( b );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_budget:
                Intent c = new Intent( Calender.this, Budget.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( c );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_goals:
                Intent d = new Intent( Calender.this, test_activity.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( d );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_settings:
                Intent e = new Intent( Calender.this, test_activity.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( e );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;
            //
            //
            //
            // Debug, remove before shipping
            case R.id.action_debug_event_form:
                Intent f = new Intent( Calender.this, Schedule_form.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( f );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;
        }
    }
    void addToView(RelativeLayout view, String text,int id)
    {
        TextView value = new TextView(this);
        value.setText(text);
        value.setId(id);
        value.setLayoutParams(new WindowManager.LayoutParams(
                //WindowManager.LayoutParams.,
                WindowManager.LayoutParams.WRAP_CONTENT));
        view.addView(value);
    }
    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();

        edHelper = new EventDataDbHelper(getApplicationContext());
        db = edHelper.getReadableDatabase();

        // Projection of what information will be used
        String[] projection = {
                EventDataContract.EventEntry._ID,
                /*EventDataContract.EventEntry.COLUMN_NAME_EVENT_NAME,
                EventDataContract.EventEntry.COLUMN_NAME_START_YEAR,
                EventDataContract.EventEntry.COLUMN_NAME_START_MONTH,*/
                EventDataContract.EventEntry.COLUMN_NAME_START_DAY
                /*EventDataContract.EventEntry.COLUMN_NAME_START_HOUR,
                EventDataContract.EventEntry.COLUMN_NAME_START_MINUTE,
                EventDataContract.EventEntry.COLUMN_NAME_END_YEAR,
                EventDataContract.EventEntry.COLUMN_NAME_END_MONTH,
                EventDataContract.EventEntry.COLUMN_NAME_END_DAY,
                EventDataContract.EventEntry.COLUMN_NAME_END_HOUR,
                EventDataContract.EventEntry.COLUMN_NAME_END_MINUTE*/
        };

        // Filter results WHERE "eventDay" = whatever day was entered, for testing
        String selection = EventDataContract.EventEntry.COLUMN_NAME_START_YEAR + " = ?" + " and " +
                EventDataContract.EventEntry.COLUMN_NAME_START_MONTH + " = ?";
        Integer checkYear = c.get(Calendar.YEAR);
        Integer checkMonth = c.get(Calendar.MONTH);

        String[] selectionArgs = { checkYear.toString(), checkMonth.toString() };
        // How results should be sorted
        String sortOrder = EventDataContract.EventEntry.COLUMN_NAME_START_DAY + " ASC";

        cursor = db.query(
                EventDataContract.EventEntry.TABLE_NAME,        // The table to query
                projection,                                     // The columns to return
                selection,                                      // The columns for the WHERE clause
                selectionArgs,                                  // The values for the WHERE clause
                null,                                           // don't group the rows
                null,                                           // don't filter by row groups
                sortOrder                                       // The sort order
        );

        boolean[] dateBools = new boolean[31];
        for (int x = 0; x < 31; x++){
            dateBools[x] = false;
        }
        while (cursor.moveToNext()){
            // Every date in dateBools that is true has an event for that day
            dateBools[cursor.getInt(cursor.getColumnIndexOrThrow(EventDataContract.EventEntry.COLUMN_NAME_START_DAY))] = true;
        }
        for(int x = 0;x < 31;x++)
        {
            if(dateBools[x] == true)
            {
                Date newDate = new Date(checkYear-1900,checkMonth,x);
                addEvent(newDate,caldroidFragment);
            }
        }
        //Refresh your stuff here
    }
}

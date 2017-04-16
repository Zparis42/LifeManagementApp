package com.lifemanagementapp.lifemanagementapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;


public class Homescreen extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
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
        setContentView(R.layout.activity_homescreen);

        // Creates new toolbar and sets it as the active action bar
        Toolbar toolbar = (Toolbar) findViewById( R.id.my_toolbar );
        setSupportActionBar( toolbar) ;

        mNavigationView = (NavigationView) findViewById( R.id.nav_view );
        mDrawerLayout = (DrawerLayout) findViewById( R.id.nav_drawer );
        mDrawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close );
        mDrawerLayout.setDrawerListener( mDrawerToggle );
        mDrawerToggle.syncState( );

        // Removes tint from menu items
        mNavigationView.setItemIconTintList(null);

        // Enables the navigation button on the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /***************************Time Line Code**************/
        listView = (ListView)findViewById(R.id.time_line_display);
        //ArrayAdapter<String> myarrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listView);
        // listView.setAdapter(myarrayAdapter);
        //listView.setTextFilterEnabled(true);

        final Calendar c = Calendar.getInstance();

        //int []Date = getIntent().getIntArrayExtra("Date");
        Month = c.get(Calendar.MONTH);
        Day = c.get(Calendar.DAY_OF_MONTH);
        Year = c.get(Calendar.YEAR);
        timelineAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(timelineAdapter);
        listView.setTextFilterEnabled(true);
        getEvents(Month,Day,Year);
        /***************************End Time Code***************/

    }

    private void selectItem(int position) {
        switch(position) {
            case 1:
                Intent a = new Intent(Homescreen.this, test_activity.class);
                startActivity(a);
                break;
            case 2:
                Intent b = new Intent(Homescreen.this, test_activity.class);
                startActivity(b);
                break;
            default:
        }
    }

    // Inflate the menu; this adds items to the action bar if it is present
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_homescreen, menu);
        return true;
    }

    // Opens navigation drawer if navigation button is clicked
    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {

        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        else {
            return true;
        }
    }

    // Puts notification on the notification drawer, each Activity will need to have its own addNotification( ) method
    public void addNotification( View view ) {

        // These three are NECESSARY for each notification, do not delete
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                // Icon for the notification
                .setSmallIcon(R.mipmap.ic_launcher)
                // Title of notification
                .setContentTitle("Test notification")
                // Text displayed under the title
                .setContentText("Finally working");

        Intent intent = new Intent(this, Homescreen.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        notificationBuilder.setContentIntent( pendingIntent );

        NotificationManager notificationManager = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );

        notificationManager.notify(0, notificationBuilder.build());

    }

    // Starts Activity based on whatever menu item is selected from navigation drawer
    public void onNavMenuItemClick( MenuItem item ) {

        switch ( item.getItemId( ) ) {
            case R.id.action_calendar:
                Intent a = new Intent( Homescreen.this, Calender.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( a );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_health:
                Intent b = new Intent( Homescreen.this, Health.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( b );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_budget:
                Intent c = new Intent( Homescreen.this, Budget.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( c );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_goals:
                Intent d = new Intent( Homescreen.this, GoalActivity.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( d );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

//            case R.id.action_settings:
//                Intent e = new Intent( Homescreen.this, Homescreen.class ); // Change "test_activity.class" to whatever class this should link to
//                startActivity( e );
//                mDrawerLayout.closeDrawer( mNavigationView );
//                break;
            //
            //
            //
            // Debug, remove before shipping
//            case R.id.action_debug_event_form:
//                Intent f = new Intent( Homescreen.this, Schedule_form.class ); // Change "test_activity.class" to whatever class this should link to
//                startActivity( f );
//                mDrawerLayout.closeDrawer( mNavigationView );
//                break;
        }
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

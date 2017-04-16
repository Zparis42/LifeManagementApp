package com.lifemanagementapp.lifemanagementapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckedTextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class GoalActivity extends AppCompatActivity {

    public static final String prefFileName = "Goal Preferences";
    public static ArrayList<String> list = new ArrayList<>();
    public static ArrayAdapter<String> goalAdapter;


    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);


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
        getSupportActionBar().setTitle("Goal");

        SharedPreferences storage = getSharedPreferences(prefFileName, MODE_PRIVATE);
        int i = 0;
        while (storage.getString(Integer.toString(i), null) != null){
            list.add(storage.getString(Integer.toString(i), "goalNotFound"));
            i++;
        }
        fillList();
    }

    private void fillList(){
        goalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, list);


        ListView listView = (ListView) findViewById(R.id.goalList);
        listView.setAdapter(goalAdapter);

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                CheckedTextView check = (CheckedTextView) view;
                check.setChecked(!check.isChecked());


            }
        });
    }


    // Inflate the menu; this adds items to the action bar if it is present
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_homescreen, menu);
        return true;
    }


    // Opens navigation drawer if navigation button is clicked
    public boolean onOptionsItemSelected( MenuItem item ) {

        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        else {
            return true;
        }
    }

    public void addNewGoal(View view){
        Intent a = new Intent( GoalActivity.this, newGoal.class);
        startActivity(a);
    }


    // Starts Activity based on whatever menu item is selected from navigation drawer
    public void onNavMenuItemClick( MenuItem item ) {

        switch ( item.getItemId( ) ) {
            case R.id.action_calendar:
                Intent a = new Intent( GoalActivity.this, Calender.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( a );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_health:
                Intent b = new Intent( GoalActivity.this, Health.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( b );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_budget:
                Intent c = new Intent( GoalActivity.this, Budget.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( c );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_homescreen:
                Intent d = new Intent( GoalActivity.this, Homescreen.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( d );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

//            case R.id.action_settings:
//                Intent e = new Intent( GoalActivity.this, Homescreen.class ); // Change "test_activity.class" to whatever class this should link to
//                startActivity( e );


//                mDrawerLayout.closeDrawer( mNavigationView );
//                break;
        }
    }

}


package com.lifemanagementapp.lifemanagementapp;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;


public class Settings extends AppCompatActivity {

    private Switch health;
    private Switch goals;
    private Switch budget;
    private Switch calendar;
    private Menu mMenu;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Create toolbar for Settings
        Toolbar toolbar = (Toolbar) findViewById( R.id.my_toolbar );
        setSupportActionBar(toolbar);

        health = (Switch) findViewById( R.id.switch_health );
        goals = (Switch) findViewById( R.id.switch_goals );
        budget = (Switch) findViewById( R.id.switch_budget );
        calendar = (Switch) findViewById( R.id.switch_calendar );

        //getSupportActionBar( ).setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    public void enableAll( View view ) {
        health.setChecked( true );
        goals.setChecked( true );
        budget.setChecked(true);
        calendar.setChecked(true);
    }

    public void disableAll( View view ) {
        health.setChecked( false );
        goals.setChecked( false );
        budget.setChecked( false );
        calendar.setChecked( false );
    }

    public void saveSettings( View view ) {
        mNavigationView = (NavigationView) findViewById( R.id.nav_view );
        Menu mMenu = mNavigationView.getMenu( );
        MenuItem iCalendar = mMenu.findItem( R.id.action_calendar );
        MenuItem iHealth = mMenu.findItem( R.id.action_health );
        MenuItem iBudget = mMenu.findItem( R.id.action_budget );
        MenuItem iGoals = mMenu.findItem( R.id.action_goals );
        if( !health.isChecked( ) ) {
            iHealth.setVisible( false );
        } else {

        }
        if( !budget.isChecked( ) ) {

        } else {

        }
        if( !calendar.isChecked( ) ) {

        } else {

        }
        if( !goals.isChecked( ) ) {

        } else {

        }
        Intent intent = new Intent(this, Homescreen.class);
        startActivity(intent);
        finish( );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        mMenu = menu;
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

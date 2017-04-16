package com.lifemanagementapp.lifemanagementapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by dfschuen on 3/31/17.
 */
public class Budget_Output extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;

    Intent intent;
    Integer healthvalue;
    Integer entertainmentvalue;
    Integer dietvalue;
    Integer householdvalue;
    Integer recreationvalue;
    Float guarenteedvalue;
    Float incomevalue;

    float newbudget;

    double adaptedhealth;
    double adaptedentertainment;
    double adapteddiet;
    double adaptedhousehold;
    double adaptedrecreation;
    double adaptedguarenteed;

    TextView healthdisplay;
    TextView entertainmentdisplay;
    TextView dietdisplay;
    TextView householddisplay;
    TextView recreationdisplay;
    TextView guarenteeddisplay;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_output);

        // Creates new toolbar and sets it as the active action bar
        Toolbar toolbar = (Toolbar) findViewById( R.id.my_toolbar );
        setSupportActionBar( toolbar) ;

        mNavigationView = (NavigationView) findViewById( R.id.nav_view );
        mDrawerLayout = (DrawerLayout) findViewById( R.id.nav_drawer );
        mDrawerToggle = new ActionBarDrawerToggle( this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        // Removes tint from menu items
        mNavigationView.setItemIconTintList(null);

        // Enables the navigation button on the toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //all of the below takes the user input from the previous form and
        //sets then to "useable" variables here
        Intent mine = getIntent();
        healthvalue = mine.getIntExtra("health", 12);
        entertainmentvalue = mine.getIntExtra("entertainment", 12);
        dietvalue = mine.getIntExtra("diet", 12);
        householdvalue = mine.getIntExtra("household", 12);
        recreationvalue = mine.getIntExtra("recreation", 12);
        guarenteedvalue = mine.getFloatExtra("guarenteed", 12);
        incomevalue = mine.getFloatExtra("income", 12);


        newbudget = (incomevalue - guarenteedvalue);
        //System.out.println("new budget is " + newbudget);

        adaptedhealth = (newbudget * (healthvalue/10f));
        //System.out.println("adapted health is " + adaptedhealth);
        adaptedentertainment = (newbudget * (entertainmentvalue/10f));
       //System.out.println("adapted entertainment is " + adaptedentertainment);
        adapteddiet = (newbudget * (dietvalue/10f));
        //System.out.println("adapted diet is " + adapteddiet);
        adaptedhousehold = (newbudget * (householdvalue/10f));
        //System.out.println("adapted household is " + adaptedhousehold);
        adaptedrecreation = (newbudget * (recreationvalue/10f));
        //System.out.println("adapted recreation is " + adaptedrecreation);
        adaptedguarenteed = (guarenteedvalue);
        //System.out.println("adapted guarenteed is " + adaptedguarenteed);

        //assigns variables to calculated input
        healthdisplay = (TextView) findViewById(R.id.health);
        entertainmentdisplay = (TextView) findViewById(R.id.entertainment);
        dietdisplay = (TextView) findViewById(R.id.diet);
        householddisplay = (TextView) findViewById(R.id.household);
        recreationdisplay = (TextView) findViewById(R.id.recreation);
        guarenteeddisplay = (TextView) findViewById(R.id.guarenteed);
        //sets the display of the form to the computed output
        healthdisplay.setText("Health spending: " + String.format("%.2f", adaptedhealth));
        entertainmentdisplay.setText("Entertainment spending: " + String.format("%.2f", adaptedentertainment));
        dietdisplay.setText("Diet spending: " + String.format("%.2f", adapteddiet));
        householddisplay.setText("Household spending: " + String.format("%.2f", adaptedhousehold));
        recreationdisplay.setText("Recreational spending: " + String.format("%.2f", adaptedrecreation));
        guarenteeddisplay.setText("Guarenteed spending: " + String.format("%.2f", adaptedguarenteed));
        //sets page title to "budget"
        getSupportActionBar().setTitle("Budget Output");

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



    // Starts Activity based on whatever menu item is selected from navigation drawer
    public void onNavMenuItemClick( MenuItem item ) {

        switch ( item.getItemId( ) ) {
            case R.id.action_calendar:
                Intent a = new Intent( Budget_Output.this, Calender.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( a );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_health:
                Intent b = new Intent( Budget_Output.this, Health.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( b );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_budget:
                Intent c = new Intent( Budget_Output.this, Budget.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( c );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_goals:
                Intent d = new Intent( Budget_Output.this, GoalActivity.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( d );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_homescreen:
                Intent e = new Intent( Budget_Output.this, Homescreen.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( e );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;
        }
    }
}

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



/**
 * Created by dfschuen on 3/20/17.
 */
public class Budget extends AppCompatActivity{
    int healthhh;
    int entertainmenttt;
    int diettt;
    int householddd;
    int recreationnn;
    float gaurenteeddd;
    float incomeee;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;
    Button mbutton;
    EditText mEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

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
        getSupportActionBar().setTitle("Budget");

    }

    
    public void buttonOnClick(View v){
        //when the button on the budget form is clicked, take the user input from all boxes
        //then assigned the class level integers to that input
        mbutton = (Button) v;
        String health = ((EditText)findViewById(R.id.health)).getText().toString();
        int healthh = Integer.parseInt(health);
        healthhh = healthh;

        String entertainment = ((EditText)findViewById(R.id.entertainment)).getText().toString();
        int entertainmentt =  Integer.parseInt(entertainment);
        entertainmenttt = entertainmentt;
        /////////////////
        String diet = ((EditText)findViewById(R.id.diet)).getText().toString();
        int diett = Integer.parseInt(diet);
        diettt = diett;

        String household = ((EditText)findViewById(R.id.household)).getText().toString();
        int householdd = Integer.parseInt(household);
        householddd = householdd;
        /////////////////////
        String recreation = ((EditText)findViewById(R.id.recreation)).getText().toString();
        int recreationn = Integer.parseInt(recreation);
        recreationnn = recreationn;

        String guarenteed = ((EditText)findViewById(R.id.gaurenteed)).getText().toString();
        float guarenteedd = Float.parseFloat(guarenteed);
        gaurenteeddd = guarenteedd;

        String income = ((EditText)findViewById(R.id.income)).getText().toString();
        float incomee = Float.parseFloat(income);
        incomeee = incomee;

        //intents below push this information into the budget output section
        Intent myIntent = new Intent(Budget.this, Budget_Output.class);
        myIntent.putExtra("health", healthhh);
        myIntent.putExtra("entertainment", entertainmenttt);



        myIntent.putExtra("diet", diettt);

      //  System.out.println("diet issss " + diettt);

        myIntent.putExtra("household", householddd);

      //  System.out.println("household issss" + householddd);

        myIntent.putExtra("recreation", recreationnn);

      //  System.out.println("recreation issss" + recreationnn);




        myIntent.putExtra("guaranteed", gaurenteeddd);
        //System.out.println("guarenteed is" + gaurenteeddd);
        myIntent.putExtra("income", incomeee);
        //System.out.println("income is " + incomeee);

        startActivity(myIntent);
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



    // Starts Activity based on whatever menu item is selected from navigation drawer
    public void onNavMenuItemClick( MenuItem item ) {

        switch ( item.getItemId( ) ) {
            case R.id.action_calendar:
                Intent a = new Intent( Budget.this, Calender.class ); //Basic navigation between budget and second .class ection
                startActivity( a );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_health:
                Intent b = new Intent( Budget.this, Health.class ); //Basic navigation between budget and second .class ection
                startActivity( b );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

//            case R.id.action_budget:
//                Intent c = new Intent( Budget.this, Budget.class ); // Change "test_activity.class" to whatever class this should link to
//                startActivity( c );
//                mDrawerLayout.closeDrawer( mNavigationView );
//                break;

            case R.id.action_goals:
                Intent d = new Intent( Budget.this, GoalActivity.class ); //Basic navigation between budget and second .class ection
                startActivity( d );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_homescreen:
                Intent e = new Intent( Budget.this, Homescreen.class ); //Basic navigation between budget and second .class ection
                startActivity( e );

            //
            //
            //
            // Debug, remove before shipping
//            case R.id.action_debug_event_form:
//                Intent f = new Intent( Budget.this, Schedule_form.class ); // Change "test_activity.class" to whatever class this should link to
//                startActivity( f );
//
//                mDrawerLayout.closeDrawer( mNavigationView );
//                break;
        }
    }

}

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
    int gaurenteeddd;
    int incomeee;
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

        String diet = ((EditText)findViewById(R.id.diet)).getText().toString();
        int diett = Integer.parseInt(health);
        diettt = diett;

        String household = ((EditText)findViewById(R.id.household)).getText().toString();
        int householdd = Integer.parseInt(health);
        householddd = householdd;

        String recreation = ((EditText)findViewById(R.id.recreation)).getText().toString();
        int recreationn = Integer.parseInt(recreation);
        recreationnn = recreationn;

        String guarenteed = ((EditText)findViewById(R.id.gaurenteed)).getText().toString();
        int guarenteedd = Integer.parseInt(guarenteed);
        gaurenteeddd = guarenteedd;

        String income = ((EditText)findViewById(R.id.income)).getText().toString();
        int incomee = Integer.parseInt(income);
        incomeee = incomee;

        Intent myIntent = new Intent(Budget.this, Budget_Output.class);
        myIntent.putExtra("health", healthhh);
        //System.out.println("health is )" + healthhh);
        myIntent.putExtra("entertainment", entertainmenttt);
        //System.out.println("entertainment is " + entertainmenttt);
        myIntent.putExtra("diet", diettt);
        //System.out.println("diet is " + diettt);
        myIntent.putExtra("household", householddd);
        //System.out.println("household is" + householddd);
        myIntent.putExtra("recreation", recreationnn);
        //System.out.println("recreation is" + recreationnn);
        myIntent.putExtra("guarenteed", gaurenteeddd);
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
                Intent a = new Intent( Budget.this, Homescreen.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( a );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

//            case R.id.action_health:
//                Intent b = new Intent( Budget.this, Health.class ); // Change "test_activity.class" to whatever class this should link to
//                startActivity( b );
//                mDrawerLayout.closeDrawer( mNavigationView );
//                break;
//
//            case R.id.action_budget:
//                Intent c = new Intent( Budget.this, Homescreen.class ); // Change "test_activity.class" to whatever class this should link to
//                startActivity( c );
//                mDrawerLayout.closeDrawer( mNavigationView );
//                break;
//
//            case R.id.action_goals:
//                Intent d = new Intent( Budget.this, Homescreen.class ); // Change "test_activity.class" to whatever class this should link to
//                startActivity( d );
//                mDrawerLayout.closeDrawer( mNavigationView );
//                break;
//
//            case R.id.action_settings:
//                Intent e = new Intent( Budget.this, Homescreen.class ); // Change "test_activity.class" to whatever class this should link to
//                startActivity( e );
//                mDrawerLayout.closeDrawer( mNavigationView );
//                break;

            //
            //
            //
            // Debug, remove before shipping
            case R.id.action_debug_event_form:
                Intent f = new Intent( Budget.this, Schedule_form.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( f );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;
        }
    }

}
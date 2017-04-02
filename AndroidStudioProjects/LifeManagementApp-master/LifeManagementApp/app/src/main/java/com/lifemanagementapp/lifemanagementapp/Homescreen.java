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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class Homescreen extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView mNavigationView;

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
                Intent b = new Intent( Homescreen.this, test_activity.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( b );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_budget:
                Intent c = new Intent( Homescreen.this, Budget.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( c );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_goals:
                Intent d = new Intent( Homescreen.this, test_activity.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( d );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;

            case R.id.action_settings:
                Intent e = new Intent( Homescreen.this, test_activity.class ); // Change "test_activity.class" to whatever class this should link to
                startActivity( e );
                mDrawerLayout.closeDrawer( mNavigationView );
                break;
        }
    }
}

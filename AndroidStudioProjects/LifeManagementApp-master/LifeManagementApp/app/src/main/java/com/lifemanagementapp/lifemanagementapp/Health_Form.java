package com.lifemanagementapp.lifemanagementapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;


public class Health_Form extends AppCompatActivity {

    public void submit( View view ) {

        EditText name = (EditText) findViewById( R.id.editTextName );
        EditText desc = (EditText) findViewById( R.id.editTextDesc );
        TimePicker time = (TimePicker) findViewById( R.id.timePicker );

        String sendName = name.getText( ).toString( );
        String sendDesc = desc.getText( ).toString( );
        String sendTime = time.getCurrentHour( ).toString( ) + ":";
        if( time.getCurrentMinute( ) < 10 ) {
            sendTime += "0" + time.getCurrentMinute( ).toString( );
        } else {
            sendTime += time.getCurrentMinute( ).toString( );
        }

        String newString = sendName + "\n" + sendDesc + "\n" + sendTime;

        // Put data into Shared Preferences
        SharedPreferences healthStorage = getSharedPreferences( Health.prefFileName, MODE_PRIVATE );
        int size = healthStorage.getInt( "HealthSize", 0 );
        SharedPreferences.Editor healthEdit = getSharedPreferences( Health.prefFileName, MODE_PRIVATE ).edit( );
        healthEdit.putString("pos_" + size, newString);
        healthEdit.putInt("HealthSize", size + 1);
        healthEdit.apply();

        Intent intent = new Intent( this, Health.class );
        startActivity( intent );
        finish( );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_form);

        // Create toolbar for Health Form
        Toolbar toolbar = (Toolbar) findViewById( R.id.my_toolbar );
        setSupportActionBar( toolbar );

        getSupportActionBar( ).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar( ).setDisplayShowHomeEnabled(true);

        // Sets the title on the toolbar
        getSupportActionBar().setTitle( "New Medication" );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_health_form, menu);
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

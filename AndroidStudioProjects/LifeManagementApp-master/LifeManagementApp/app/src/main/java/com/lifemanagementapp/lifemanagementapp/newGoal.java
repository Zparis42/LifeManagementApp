package com.lifemanagementapp.lifemanagementapp;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RatingBar;


public class newGoal extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_goal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void createNewGoal(View button){
        final EditText goalNameField = (EditText) findViewById(R.id.EditGoalName);
        String goalName = goalNameField.getText().toString();

        final DatePicker datepicker = (DatePicker) findViewById(R.id.datePicker);
        int day = datepicker.getDayOfMonth();
        int month = datepicker.getMonth();
        int year = datepicker.getYear();

        final RatingBar priorityRating = (RatingBar) findViewById(R.id.ratingBar);
        int priority = priorityRating.getNumStars();

        Goal.goalCount ++;
        int storagePosition = Goal.goalCount;

        Goal addGoal = new Goal(goalName, day, month, year, priority, storagePosition);
        GoalActivity.list.add(addGoal.toString());
        GoalActivity.goalAdapter.notifyDataSetChanged();
        SharedPreferences.Editor editor = getSharedPreferences(GoalActivity.prefFileName, MODE_PRIVATE).edit();
        editor.putString(Integer.toString(storagePosition), addGoal.toString());
        editor.apply();



        finish();
    }

    public void cancelForm(View button){
        finish();
    }
}

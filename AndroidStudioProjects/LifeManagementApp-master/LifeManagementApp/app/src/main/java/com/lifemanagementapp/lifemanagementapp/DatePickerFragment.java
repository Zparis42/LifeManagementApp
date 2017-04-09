package com.lifemanagementapp.lifemanagementapp;

import android.app.Activity;
import android.app.Dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by Zac on 3/21/2017.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DateFragmentInteractionListener mListener;

    private int year;
    private int month;
    private int day;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    public DatePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        //final Calendar c = Calendar.getInstance();
        Bundle args = getArguments();
        year = args.getInt(ARG_PARAM1);
        month = args.getInt(ARG_PARAM2);
        day = args.getInt(ARG_PARAM3);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public static DatePickerFragment newInstance(int setYear, int setMonth, int setDay) {
        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, setYear);
        args.putInt(ARG_PARAM2, setMonth);
        args.putInt(ARG_PARAM3, setDay);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (DateFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        // Do something with date chosen by user
        year = i;
        month = i1;
        day = i2;
        mListener.DateFragmentInteraction(year, month, day);
    }

    public interface DateFragmentInteractionListener {
        // TODO: Update argument type and name
        public void DateFragmentInteraction(int year, int month, int day);
    }
}

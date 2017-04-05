// Zac's event form time picker
package com.lifemanagementapp.lifemanagementapp;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimePickerFragment.TimeFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

// NOTE: I don't know what most of this does, I used a template to create this.
public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TimeFragmentInteractionListener mListener;

    private int hour;
    private int minute;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param setHour Parameter 1.
     * @param setMinute Parameter 2.
     * @return A new instance of fragment TimePickerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimePickerFragment newInstance(int setHour, int setMinute) {
        TimePickerFragment fragment = new TimePickerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, setHour);
        args.putInt(ARG_PARAM2, setMinute);
        fragment.setArguments(args);
        return fragment;
    }

    // TODO: Update this
    // When the time is set, send info back to form through onFragmentInteraction
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
        mListener.TimeFragmentInteraction(hourOfDay, minute);
    }

    public TimePickerFragment() {
        // Required empty public constructor
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        Bundle args = getArguments();
        //final Calendar c = Calendar.getInstance();
        int hour = args.getInt(ARG_PARAM1);
        int minute = args.getInt(ARG_PARAM2);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        //textView.setText();
        return textView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.TimeFragmentInteraction(-1, -1);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (TimeFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface TimeFragmentInteractionListener {
        // TODO: Update argument type and name
        public void TimeFragmentInteraction(int hour, int minute);
    }

}

package com.lifemanagementapp.lifemanagementapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public final class EventDataContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private EventDataContract() {

    }

    // Inner class that defines the table contents
    public static class EventEntry implements BaseColumns {
        public static final String TABLE_NAME = "events";
        public static final String COLUMN_NAME_EVENT_NAME = "eventName";
        public static final String COLUMN_NAME_START_YEAR = "startYear";
        public static final String COLUMN_NAME_START_MONTH = "startMonth";
        public static final String COLUMN_NAME_START_DAY = "startDay";
        public static final String COLUMN_NAME_START_HOUR = "startHour";
        public static final String COLUMN_NAME_START_MINUTE = "startMinute";

        public static final String COLUMN_NAME_END_YEAR = "endYear";
        public static final String COLUMN_NAME_END_MONTH = "endMonth";
        public static final String COLUMN_NAME_END_DAY = "endDay";
        public static final String COLUMN_NAME_END_HOUR = "endHour";
        public static final String COLUMN_NAME_END_MINUTE = "endMinute";
        // Add others here
    }

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EventEntry.TABLE_NAME + " (" +
            EventEntry._ID + " INTEGER PRIMARY KEY," +
            EventEntry.COLUMN_NAME_EVENT_NAME + " TEXT," +
            EventEntry.COLUMN_NAME_START_YEAR + " INTEGER," +
            EventEntry.COLUMN_NAME_START_MONTH + " INTEGER," +
            EventEntry.COLUMN_NAME_START_DAY + " INTEGER," +
            EventEntry.COLUMN_NAME_START_HOUR + " INTEGER," +
            EventEntry.COLUMN_NAME_START_MINUTE + " INTEGER +" +
            EventEntry.COLUMN_NAME_END_YEAR + " INTEGER," +
            EventEntry.COLUMN_NAME_END_MONTH + " INTEGER," +
            EventEntry.COLUMN_NAME_END_DAY + " INTEGER," +
            EventEntry.COLUMN_NAME_END_HOUR + " INTEGER," +
            EventEntry.COLUMN_NAME_END_MINUTE + " INTEGER)";
            // Change this if schema is updated

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME;


}




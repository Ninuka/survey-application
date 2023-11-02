package com.example.surveyapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "survey_database";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "survey_data";
    private static final String COLUMN_LATITUDE = "latitude";
    private static final String COLUMN_LONGITUDE = "longitude";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_VOTERS = "voters";
    private static final String COLUMN_EXPECTED_VOTES = "expected_votes";
    private static final String COLUMN_FEEDBACK_TYPE = "feedback_type";
    private static final String COLUMN_SUPPORTED_PARTY = "supported_party";
    private static final String COLUMN_NATURE_OF_SUPPORT = "nature_of_support";
    private static final String COLUMN_ADDITIONAL_DETAILS = "additional_details";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_LATITUDE + " REAL, " +
                COLUMN_LONGITUDE + " REAL, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_VOTERS + " INTEGER, " +
                COLUMN_EXPECTED_VOTES + " INTEGER, " +
                COLUMN_FEEDBACK_TYPE + " TEXT, " +
                COLUMN_SUPPORTED_PARTY + " TEXT, " +
                COLUMN_NATURE_OF_SUPPORT + " TEXT, " +
                COLUMN_ADDITIONAL_DETAILS + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertSurveyData(double latitude, double longitude, String date, String time,
                                 int voters, int expectedVotes, String feedbackType,
                                 String supportedParty, String natureOfSupport, String additionalDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_LATITUDE, latitude);
        values.put(COLUMN_LONGITUDE, longitude);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_TIME, time);
        values.put(COLUMN_VOTERS, voters);
        values.put(COLUMN_EXPECTED_VOTES, expectedVotes);
        values.put(COLUMN_FEEDBACK_TYPE, feedbackType);
        values.put(COLUMN_SUPPORTED_PARTY, supportedParty);
        values.put(COLUMN_NATURE_OF_SUPPORT, natureOfSupport);
        values.put(COLUMN_ADDITIONAL_DETAILS, additionalDetails);
        db.insert(TABLE_NAME, null, values);
        db.close();

    }
    public Cursor getAllSurveyData(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

}

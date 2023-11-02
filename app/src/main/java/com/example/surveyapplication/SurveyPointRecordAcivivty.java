package com.example.surveyapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SurveyPointRecordAcivivty extends AppCompatActivity {
    private EditText latitudeEditText;
    private EditText longitudeEditText;
    private EditText dateEditText;
    private EditText timeEditText;
    private EditText votersEditText;
    private EditText expectedVotesEditText;
    private EditText feedbackTypeEditText;
    private EditText supportedPartyEditText;
    private EditText natureOfSupportEditText;
    private EditText additionalDetailsEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_point_record_acivivty);
        latitudeEditText = findViewById(R.id.latitudeEditText);
        longitudeEditText = findViewById(R.id.longitudeEditText);
        dateEditText = findViewById(R.id.dateTimeEditText);
        timeEditText = findViewById(R.id.dateTimeEditText);
        votersEditText = findViewById(R.id.numVotersEditText);
        expectedVotesEditText = findViewById(R.id.numVotesExpectedEditText);
        feedbackTypeEditText = findViewById(R.id.feedbackTypeEditText);
        supportedPartyEditText = findViewById(R.id.supportingPartyEditText);
        natureOfSupportEditText = findViewById(R.id.supportNatureEditText);
        additionalDetailsEditText = findViewById(R.id.additionalDetailsEditText);
        saveButton = findViewById(R.id.saveButton);


        // Retrieve latitude and longitude values from the intent
        double latitude = getIntent().getDoubleExtra("LATITUDE", 0.0);
        double longitude = getIntent().getDoubleExtra("LONGITUDE", 0.0);

        // Set the retrieved values in the EditText fields
        latitudeEditText.setText(String.valueOf(latitude));
        longitudeEditText.setText(String.valueOf(longitude));

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = dateEditText.getText().toString();
                String time = timeEditText.getText().toString();
                int voters = Integer.parseInt(votersEditText.getText().toString());
                int expectedVotes = Integer.parseInt(expectedVotesEditText.getText().toString());
                String feedbackType = feedbackTypeEditText.getText().toString();
                String supportedParty = supportedPartyEditText.getText().toString();
                String natureOfSupport = natureOfSupportEditText.getText().toString();
                String additionalDetails = additionalDetailsEditText.getText().toString();

                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
                dbHelper.insertSurveyData(latitude, longitude, date, time, voters, expectedVotes,
                        feedbackType, supportedParty, natureOfSupport, additionalDetails);
                Toast.makeText(getApplicationContext(), "Survey data saved successfully", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }
}
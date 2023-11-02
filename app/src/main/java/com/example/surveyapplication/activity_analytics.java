package com.example.surveyapplication;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

class AnalyticsActivity extends AppCompatActivity {

    private TextView totalRecordsTextView;
    private TextView feedbackStatisticsTextView;
    private TextView chancesOfWinningTextView;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);

        totalRecordsTextView = findViewById(R.id.totalRecordsTextView);
        feedbackStatisticsTextView = findViewById(R.id.feedbackStatisticsTextView);
        chancesOfWinningTextView = findViewById(R.id.chancesOfWinningTextView);

        databaseHelper = new DatabaseHelper(this);


        generateAnalytics();
    }

    private void generateAnalytics() {

        Cursor surveyData = databaseHelper.getAllSurveyData();


        int totalRecords = surveyData.getCount();
        totalRecordsTextView.setText("Total Records: " + totalRecords);


        int positiveFeedbackCount = 0;
        int negativeFeedbackCount = 0;
        int neutralFeedbackCount = 0;

        while (surveyData.moveToNext()) {
            @SuppressLint("Range") String feedbackType = surveyData.getString(surveyData.getColumnIndex("feedback_type"));

            if ("positive".equals(feedbackType)) {
                positiveFeedbackCount++;
            } else if ("negative".equals(feedbackType)) {
                negativeFeedbackCount++;
            } else if ("neutral".equals(feedbackType)) {
                neutralFeedbackCount++;
            }
        }


        String feedbackStatistics = "Positive Feedback: " + positiveFeedbackCount +
                "\nNegative Feedback: " + negativeFeedbackCount +
                "\nNeutral Feedback: " + neutralFeedbackCount;

        feedbackStatisticsTextView.setText(feedbackStatistics);


        boolean isWinning = positiveFeedbackCount > negativeFeedbackCount;


        String winningStatus = isWinning ? "Party has high chances of winning!" :
                "Party needs to work harder to win!";
        chancesOfWinningTextView.setText(winningStatus);

        surveyData.close();
    }
}

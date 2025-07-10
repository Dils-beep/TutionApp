package com.example.tutionapp;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
public class StudentDashboardActivity extends AppCompatActivity {

    CardView cardViewAttendance, cardSubmitAssignments, cardViewResults, cardDownloadMaterials, cardReceiveNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        cardViewAttendance = findViewById(R.id.cardViewAttendance);
        cardSubmitAssignments = findViewById(R.id.cardSubmitAssignments);
        cardViewResults = findViewById(R.id.cardViewResults);
        cardDownloadMaterials = findViewById(R.id.cardDownloadMaterials);
        cardReceiveNotifications = findViewById(R.id.cardReceiveNotifications);

        cardViewAttendance.setOnClickListener(v -> startActivity(new Intent(this, ViewAttendanceActivity.class)));
        cardSubmitAssignments.setOnClickListener(v -> startActivity(new Intent(this, AssignmentActivity.class)));
        cardViewResults.setOnClickListener(v -> startActivity(new Intent(this, ResultsActivity.class)));
        cardDownloadMaterials.setOnClickListener(v -> startActivity(new Intent(this, MaterialsActivity.class)));
        cardReceiveNotifications.setOnClickListener(v -> startActivity(new Intent(this, NotificationActivity.class)));
    }
}


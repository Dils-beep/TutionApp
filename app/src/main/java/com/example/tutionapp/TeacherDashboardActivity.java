package com.example.tutionapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TeacherDashboardActivity extends AppCompatActivity {

    CardView cardTakeAttendance, cardUploadResults, cardUploadMaterials, cardViewStudents, cardSendNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        cardTakeAttendance = findViewById(R.id.cardTakeAttendance);
        cardUploadResults = findViewById(R.id.cardUploadResults);
        cardUploadMaterials = findViewById(R.id.cardUploadMaterials);
        cardViewStudents = findViewById(R.id.cardViewStudents);
        cardSendNotifications = findViewById(R.id.cardSendNotifications);

        cardTakeAttendance.setOnClickListener(v -> startActivity(new Intent(this, TakeAttendanceActivity.class)));
        cardUploadResults.setOnClickListener(v -> startActivity(new Intent(this, UploadResultsActivity.class)));
        cardUploadMaterials.setOnClickListener(v -> startActivity(new Intent(this, UploadMaterialsActivity.class)));
        cardViewStudents.setOnClickListener(v -> startActivity(new Intent(this, ViewStudentsActivity.class)));
        cardSendNotifications.setOnClickListener(v -> startActivity(new Intent(this, SendNotificationsActivity.class)));
    }
}

package com.example.tutionapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {

    Button btnManageStudents, btnManageTeachers, btnAssignCourses, btnViewReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        btnManageStudents = findViewById(R.id.btnManageStudents);
        btnManageTeachers = findViewById(R.id.btnManageTeachers);
        btnAssignCourses = findViewById(R.id.btnAssignCourses);
        btnViewReports = findViewById(R.id.btnViewReports);

        // Later you'll connect these buttons to activities
        btnManageStudents.setOnClickListener(v -> {
            startActivity(new Intent(this, ManageStudentsActivity.class));
        });

        btnManageTeachers.setOnClickListener(v -> {
            startActivity(new Intent(this, ManageTeachersActivity.class));
        });

        btnAssignCourses.setOnClickListener(v -> {
            startActivity(new Intent(this, AssignCoursesActivity.class));
        });

        btnViewReports.setOnClickListener(v -> {
            startActivity(new Intent(this, ReportsActivity.class));
        });
    }
}

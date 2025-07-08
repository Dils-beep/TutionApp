package com.example.tutionapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.cardview.widget.CardView;
import androidx.appcompat.app.AppCompatActivity;

public class SelectUserActivity extends AppCompatActivity {

    private CardView cardTeacher, cardStudent, cardAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectuser);

        // Link UI components
        cardTeacher = findViewById(R.id.cardTeacher);
        cardStudent = findViewById(R.id.cardStudent);
        cardAdmin = findViewById(R.id.cardAdmin);

        // Handle Teacher click
        cardTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Replace with actual Teacher login/activity
                Intent intent = new Intent(SelectUserActivity.this, TeacherLoginActivity.class);
                startActivity(intent);
            }
        });

        // Handle Student click
        cardStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectUserActivity.this, StudentLoginActivity.class);
                startActivity(intent);
            }
        });

        // Handle Admin click
        cardAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectUserActivity.this, AdminLoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

package com.example.tutionapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddStudentActivity extends AppCompatActivity {

    EditText etName, etUsername, etEmail, etPhone, etGrade, etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etName = findViewById(R.id.etStudentName);
        etUsername = findViewById(R.id.etStudentUsername);
        etEmail = findViewById(R.id.etStudentEmail);
        etPhone = findViewById(R.id.etStudentPhone);
        etGrade = findViewById(R.id.etStudentGrade);
        etPassword = findViewById(R.id.etStudentPassword);
        btnRegister = findViewById(R.id.btnRegisterStudent);

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String grade = etGrade.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (name.isEmpty() || username.isEmpty() || email.isEmpty() ||
                    phone.isEmpty() || grade.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else {
                // TODO: Save to database
                Toast.makeText(this, "Student Registered: " + username, Toast.LENGTH_SHORT).show();
                finish();
            }

        });
        // Inside onCreate()
        Intent intent = getIntent();
        String studentId = intent.getStringExtra("studentId");
        if (studentId != null) {
            // Update mode: pre-fill fields
            etName.setText(intent.getStringExtra("name"));
            etUsername.setText(intent.getStringExtra("username"));
            etEmail.setText(intent.getStringExtra("email"));
            etPhone.setText(intent.getStringExtra("phone"));
            etGrade.setText(intent.getStringExtra("grade"));

            btnRegister.setText("Update Student");

            btnRegister.setOnClickListener(v -> {
                // Update logic using studentId key in database
            });
        } else {
            // Add mode
        }

    }
}

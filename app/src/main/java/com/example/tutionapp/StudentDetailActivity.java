package com.example.tutionapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentDetailActivity extends AppCompatActivity {

    TextView tvName, tvUsername, tvEmail, tvPhone, tvGrade;
    Button btnUpdate, btnDelete;

    String studentId, name, username, email, phone, grade;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        tvName = findViewById(R.id.tvName);
        tvUsername = findViewById(R.id.tvUsername);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvGrade = findViewById(R.id.tvGrade);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        // Get data from Intent
        Intent intent = getIntent();
        studentId = intent.getStringExtra("studentId");
        name = intent.getStringExtra("name");
        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");
        grade = intent.getStringExtra("grade");

        // Show data
        tvName.setText("Name: " + name);
        tvUsername.setText("Username: " + username);
        tvEmail.setText("Email: " + email);
        tvPhone.setText("Phone: " + phone);
        tvGrade.setText("Grade: " + grade);

        dbRef = FirebaseDatabase.getInstance().getReference("Students").child(studentId);

        // Update button opens AddStudentActivity with data to edit
        btnUpdate.setOnClickListener(v -> {
            Intent editIntent = new Intent(StudentDetailActivity.this, AddStudentActivity.class);
            editIntent.putExtra("studentId", studentId);
            editIntent.putExtra("name", name);
            editIntent.putExtra("username", username);
            editIntent.putExtra("email", email);
            editIntent.putExtra("phone", phone);
            editIntent.putExtra("grade", grade);
            // optionally add password if you want
            startActivity(editIntent);
            finish();
        });

        // Delete confirmation dialog
        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Student")
                    .setMessage("Are you sure you want to delete " + name + "?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        dbRef.removeValue().addOnSuccessListener(unused -> {
                            Toast.makeText(this, "Student deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        });
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}

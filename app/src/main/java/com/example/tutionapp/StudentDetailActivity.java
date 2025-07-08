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

    TextView tvName, tvUsername, tvPassword, tvEmail, tvPhone, tvGrade, tvSubjects;
    Button btnUpdate, btnDelete;

    String studentId, name, username, email, phone, grade, subjects;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        tvName = findViewById(R.id.tvName);
        tvUsername = findViewById(R.id.tvUsername);
        tvPassword = findViewById(R.id.tvPassword);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        tvGrade = findViewById(R.id.tvGrade);
        tvSubjects = findViewById(R.id.tvSubjects);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        studentId = intent.getStringExtra("studentId");
        name = intent.getStringExtra("name");
        username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        email = intent.getStringExtra("email");
        phone = intent.getStringExtra("phone");
        grade = intent.getStringExtra("grade");
        subjects = intent.getStringExtra("subjects");

        tvName.setText("Name: " + name);
        tvUsername.setText("Username: " + username);
        tvPassword.setText("Password: " + password);
        tvEmail.setText("Email: " + email);
        tvPhone.setText("Phone: " + phone);
        tvGrade.setText("Grade: " + grade);
        tvSubjects.setText("Subjects: " + subjects);

        dbRef = FirebaseDatabase.getInstance().getReference("Students");

        btnUpdate.setOnClickListener(v -> {
            Intent updateIntent = new Intent(StudentDetailActivity.this, AddStudentActivity.class);
            updateIntent.putExtra("studentId", studentId);
            updateIntent.putExtra("name", name);
            updateIntent.putExtra("username", username);
            updateIntent.putExtra("password", password);
            updateIntent.putExtra("email", email);
            updateIntent.putExtra("phone", phone);
            updateIntent.putExtra("grade", grade);
            updateIntent.putExtra("subjects", subjects);
            startActivity(updateIntent);
            finish();
        });

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(StudentDetailActivity.this)
                    .setTitle("Delete Student")
                    .setMessage("Are you sure you want to delete this student?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        dbRef.child(studentId).removeValue().addOnSuccessListener(unused -> {
                            Toast.makeText(StudentDetailActivity.this, "Student deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }).addOnFailureListener(e -> {
                            Toast.makeText(StudentDetailActivity.this, "Failed to delete student", Toast.LENGTH_SHORT).show();
                        });
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}

package com.example.tutionapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    EditText etName, etUsername, etPassword, etEmail, etPhone, etGrade;
    CheckBox cbEnglish, cbMath, cbScience, cbHistory, cbSinhala, cbCommerce, cbICT;
    Button btnRegister;

    DatabaseReference dbRef;
    String studentId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        // In onCreate():
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etGrade = findViewById(R.id.etGrade);

        cbEnglish = findViewById(R.id.cbEnglish);
        cbMath = findViewById(R.id.cbMath);
        cbScience = findViewById(R.id.cbScience);
        cbHistory = findViewById(R.id.cbHistory);
        cbSinhala = findViewById(R.id.cbSinhala);
        cbCommerce = findViewById(R.id.cbCommerce);
        cbICT = findViewById(R.id.cbICT);

        btnRegister = findViewById(R.id.btnRegister);

        dbRef = FirebaseDatabase.getInstance().getReference("Students");

        // Check if editing existing student
        Intent intent = getIntent();
        studentId = intent.getStringExtra("studentId");

        if (studentId != null) {
            // Update mode - pre-fill fields
            etName.setText(intent.getStringExtra("name"));
            etUsername.setText(intent.getStringExtra("username"));
            etPassword.setText(intent.getStringExtra("password"));
            etEmail.setText(intent.getStringExtra("email"));
            etPhone.setText(intent.getStringExtra("phone"));
            etGrade.setText(intent.getStringExtra("grade"));
            String subjectsStr = intent.getStringExtra("subjects");
            precheckSubjects(subjectsStr);

            btnRegister.setText("Update Student");
        } else {
            btnRegister.setText("Register Student");
        }

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String grade = etGrade.getText().toString().trim();

            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter Name, Username and Password", Toast.LENGTH_SHORT).show();
                return;
            }

            String subjects = getSelectedSubjects();

            if (studentId == null) {
                // New student
                String id = dbRef.push().getKey();
                StudentModel student = new StudentModel(id, name, username,password, email, phone, grade, subjects);
                dbRef.child(id).setValue(student).addOnSuccessListener(unused -> {
                    Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to add student", Toast.LENGTH_SHORT).show();
                });
            } else {
                // Update existing student
                StudentModel student = new StudentModel(studentId, name, username,password, email, phone, grade, subjects);
                dbRef.child(studentId).setValue(student).addOnSuccessListener(unused -> {
                    Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show();
                    finish();
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to update student", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private String getSelectedSubjects() {
        List<String> selectedSubjects = new ArrayList<>();
        if (cbEnglish.isChecked()) selectedSubjects.add("English Language");
        if (cbMath.isChecked()) selectedSubjects.add("Mathematics");
        if (cbScience.isChecked()) selectedSubjects.add("Science");
        if (cbHistory.isChecked()) selectedSubjects.add("History");
        if (cbSinhala.isChecked()) selectedSubjects.add("Sinhala");
        if (cbCommerce.isChecked()) selectedSubjects.add("Commerce");
        if (cbICT.isChecked()) selectedSubjects.add("ICT");
        return TextUtils.join(", ", selectedSubjects);
    }

    private void precheckSubjects(String subjectsStr) {
        if (subjectsStr == null) return;
        if (subjectsStr.contains("English Language")) cbEnglish.setChecked(true);
        if (subjectsStr.contains("Mathematics")) cbMath.setChecked(true);
        if (subjectsStr.contains("Science")) cbScience.setChecked(true);
        if (subjectsStr.contains("History")) cbHistory.setChecked(true);
        if (subjectsStr.contains("Sinhala")) cbSinhala.setChecked(true);
        if (subjectsStr.contains("Commerce")) cbCommerce.setChecked(true);
        if (subjectsStr.contains("ICT")) cbICT.setChecked(true);
    }
}

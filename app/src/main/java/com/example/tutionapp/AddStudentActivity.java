package com.example.tutionapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    private EditText etName, etUsername, etPassword, etEmail, etPhone, etGrade;
    private CheckBox cbMath, cbScience, cbEnglish, cbICT, cbSinhala, cbHistory, cbCommerce;
    private Button btnRegister;

    private FirebaseAuth mAuth;
    private DatabaseReference studentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        etName = findViewById(R.id.etName);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etGrade = findViewById(R.id.etGrade);

        cbMath = findViewById(R.id.cbMath);
        cbScience = findViewById(R.id.cbScience);
        cbEnglish = findViewById(R.id.cbEnglish);
        cbICT = findViewById(R.id.cbICT);
        cbSinhala = findViewById(R.id.cbSinhala);
        cbHistory = findViewById(R.id.cbHistory);
        cbCommerce = findViewById(R.id.cbCommerce);

        btnRegister = findViewById(R.id.btnRegister);

        mAuth = FirebaseAuth.getInstance();
        studentRef = FirebaseDatabase.getInstance().getReference("students");

        btnRegister.setOnClickListener(view -> registerStudent());
    }

    private void registerStudent() {
        String name = etName.getText().toString().trim();
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String grade = etGrade.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password)
                || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(grade)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        List<String> selectedSubjects = new ArrayList<>();
        if (cbMath.isChecked()) selectedSubjects.add("Mathematics");
        if (cbScience.isChecked()) selectedSubjects.add("Science");
        if (cbEnglish.isChecked()) selectedSubjects.add("English Language");
        if (cbICT.isChecked()) selectedSubjects.add("ICT");
        if (cbSinhala.isChecked()) selectedSubjects.add("Sinhala");
        if (cbHistory.isChecked()) selectedSubjects.add("History");
        if (cbCommerce.isChecked()) selectedSubjects.add("Commerce");

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    String uid = authResult.getUser().getUid();
                    Student student = new Student(name, username, password, email, phone, grade, selectedSubjects);
                    studentRef.child(uid).setValue(student)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(this, "Student registered successfully", Toast.LENGTH_SHORT).show();
                                clearFields();
                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_LONG).show());
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Auth failed: " + e.getMessage(), Toast.LENGTH_LONG).show());
    }

    private void clearFields() {
        etName.setText("");
        etUsername.setText("");
        etPassword.setText("");
        etEmail.setText("");
        etPhone.setText("");
        etGrade.setText("");
        cbMath.setChecked(false);
        cbScience.setChecked(false);
        cbEnglish.setChecked(false);
        cbICT.setChecked(false);
        cbSinhala.setChecked(false);
        cbHistory.setChecked(false);
        cbCommerce.setChecked(false);
    }

    public static class Student {
        public String name, username, password, email, phone, grade;
        public List<String> subjects;

        public Student() {} // Required for Firebase

        public Student(String name, String username, String password, String email, String phone, String grade, List<String> subjects) {
            this.name = name;
            this.username = username;
            this.password = password;
            this.email = email;
            this.phone = phone;
            this.grade = grade;
            this.subjects = subjects;
        }
    }
}

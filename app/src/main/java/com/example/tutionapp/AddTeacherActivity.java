package com.example.tutionapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTeacherActivity extends AppCompatActivity {

    private EditText etTeacherName, etTeacherUsername, etTeacherPassword, etTeacherEmail, etTeacherPhone, etTeacherSubject;
    private Button btnSaveTeacher;

    private FirebaseAuth mAuth;
    private DatabaseReference teacherRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        // Initialize views
        etTeacherName = findViewById(R.id.etTeacherName);
        etTeacherUsername = findViewById(R.id.etTeacherUsername);
        etTeacherPassword = findViewById(R.id.etTeacherPassword);
        etTeacherEmail = findViewById(R.id.etTeacherEmail);
        etTeacherPhone = findViewById(R.id.etTeacherPhone);
        etTeacherSubject = findViewById(R.id.etTeacherSubject);
        btnSaveTeacher = findViewById(R.id.btnSaveTeacher);

        // Firebase
        mAuth = FirebaseAuth.getInstance();
        teacherRef = FirebaseDatabase.getInstance().getReference("teachers");

        btnSaveTeacher.setOnClickListener(v -> registerTeacher());
    }

    private void registerTeacher() {
        String name = etTeacherName.getText().toString().trim();
        String username = etTeacherUsername.getText().toString().trim();
        String password = etTeacherPassword.getText().toString().trim();
        String email = etTeacherEmail.getText().toString().trim();
        String phone = etTeacherPhone.getText().toString().trim();
        String subject = etTeacherSubject.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(username) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(email) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(subject)) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    String uid = authResult.getUser().getUid();

                    // ✅ Save password too
                    Teacher teacher = new Teacher(name, username, email, phone, subject, password);

                    teacherRef.child(uid).setValue(teacher)
                            .addOnSuccessListener(aVoid -> {
                                Toast.makeText(this, "Teacher registered successfully", Toast.LENGTH_SHORT).show();
                                clearFields();
                                mAuth.signOut();
                            })
                            .addOnFailureListener(e ->
                                    Toast.makeText(this, "Failed to save data: " + e.getMessage(), Toast.LENGTH_SHORT).show());
                })
                .addOnFailureListener(e ->
                        Toast.makeText(this, "Auth failed: " + e.getMessage(), Toast.LENGTH_LONG).show());
    }

    private void clearFields() {
        etTeacherName.setText("");
        etTeacherUsername.setText("");
        etTeacherPassword.setText("");
        etTeacherEmail.setText("");
        etTeacherPhone.setText("");
        etTeacherSubject.setText("");
    }

    // ✅ UPDATED Teacher model class (includes password)
    public static class Teacher {
        public String name, username, email, phone, subject, password;

        public Teacher() {} // Default constructor for Firebase

        public Teacher(String name, String username, String email, String phone, String subject, String password) {
            this.name = name;
            this.username = username;
            this.email = email;
            this.phone = phone;
            this.subject = subject;
            this.password = password;
        }
    }
}

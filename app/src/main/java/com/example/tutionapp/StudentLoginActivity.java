package com.example.tutionapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentLoginActivity extends AppCompatActivity {

    private EditText etUsername, etPassword;
    private Button btnStudentLogin;

    private DatabaseReference studentRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginstudent);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnStudentLogin = findViewById(R.id.btnStudentLogin);

        studentRef = FirebaseDatabase.getInstance().getReference("students");

        btnStudentLogin.setOnClickListener(view -> loginStudent());
    }

    private void loginStudent() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Look through all students to match the username & password
        studentRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                boolean found = false;
                for (DataSnapshot studentSnap : task.getResult().getChildren()) {
                    String storedUsername = studentSnap.child("username").getValue(String.class);
                    String storedPassword = studentSnap.child("password").getValue(String.class);

                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        found = true;
                        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

                        // You can pass data or navigate to student dashboard
                        Intent intent = new Intent(this, StudentDashboardActivity.class);
                        intent.putExtra("studentId", studentSnap.getKey());
                        startActivity(intent);
                        finish();
                        break;
                    }
                }

                if (!found) {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Database error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

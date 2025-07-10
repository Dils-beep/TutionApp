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
import com.google.firebase.database.ValueEventListener;

public class TeacherLoginActivity extends AppCompatActivity {

    private EditText etTeacherUsername, etTeacherPassword;
    private Button btnTeacherLogin;
    private DatabaseReference teacherRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginteacher); // Make sure your XML file is named correctly

        etTeacherUsername = findViewById(R.id.etTeacherUsername);
        etTeacherPassword = findViewById(R.id.etTeacherPassword);
        btnTeacherLogin = findViewById(R.id.btnTeacherLogin);

        teacherRef = FirebaseDatabase.getInstance().getReference("teachers");

        btnTeacherLogin.setOnClickListener(v -> {
            String inputUsername = etTeacherUsername.getText().toString().trim();
            String inputPassword = etTeacherPassword.getText().toString().trim();

            if (TextUtils.isEmpty(inputUsername) || TextUtils.isEmpty(inputPassword)) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            // Firebase DB check
            teacherRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    boolean found = false;

                    for (DataSnapshot teacherSnap : snapshot.getChildren()) {
                        String username = teacherSnap.child("username").getValue(String.class);
                        String password = teacherSnap.child("password").getValue(String.class);

                        if (username != null && password != null &&
                                username.equals(inputUsername) && password.equals(inputPassword)) {
                            found = true;
                            break;
                        }
                    }

                    if (found) {
                        Toast.makeText(TeacherLoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TeacherLoginActivity.this, TeacherDashboardActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(TeacherLoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(TeacherLoginActivity.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}

package com.example.tutionapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class AdminLoginActivity extends AppCompatActivity {

    private EditText etAdminUsername, etAdminPassword;
    private Button btnAdminLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginadmin);

        etAdminUsername = findViewById(R.id.etAdminUsername);
        etAdminPassword = findViewById(R.id.etAdminPassword);
        btnAdminLogin = findViewById(R.id.btnAdminLogin);
        mAuth = FirebaseAuth.getInstance();

        btnAdminLogin.setOnClickListener(v -> loginAdmin());
    }

    private void loginAdmin() {
        String email = etAdminUsername.getText().toString().trim();  // email goes here
        String password = etAdminPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, AdminDashboardActivity.class));
                        finish();
                    } else {
                        Toast.makeText(this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}

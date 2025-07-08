package com.example.tutionapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TeacherLoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginteacher);

        username = findViewById(R.id.etTeacherUsername);
        password = findViewById(R.id.etTeacherPassword);
        btnLogin = findViewById(R.id.btnTeacherLogin);

        btnLogin.setOnClickListener(v -> {
            String user = username.getText().toString();
            String pass = password.getText().toString();
            Toast.makeText(this, "Teacher Login: " + user, Toast.LENGTH_SHORT).show();
            // TODO: Add validation and next activity
        });
    }
}

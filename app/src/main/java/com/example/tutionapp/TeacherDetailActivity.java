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

public class TeacherDetailActivity extends AppCompatActivity {

    TextView tvName, tvUsername, tvPassword, tvEmail, tvPhone, tvSubject;
    Button btnUpdate, btnDelete;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_detail);

        tvName = findViewById(R.id.tvTeacherNameDetail);
        tvUsername = findViewById(R.id.tvTeacherUsernameDetail);
        tvPassword = findViewById(R.id.tvTeacherPasswordDetail);
        tvEmail = findViewById(R.id.tvTeacherEmailDetail);
        tvPhone = findViewById(R.id.tvTeacherPhoneDetail);
        tvSubject = findViewById(R.id.tvTeacherSubjectDetail);

        btnUpdate = findViewById(R.id.btnUpdateTeacher);
        btnDelete = findViewById(R.id.btnDeleteTeacher);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        String password = intent.getStringExtra("password");
        String email = intent.getStringExtra("email");
        String phone = intent.getStringExtra("phone");
        String subject = intent.getStringExtra("subject");

        tvName.setText("Name: " + name);
        tvUsername.setText("Username: " + username);
        tvPassword.setText("Password: " + password);
        tvEmail.setText("Email: " + email);
        tvPhone.setText("Phone: " + phone);
        tvSubject.setText("Subject: " + subject);

        dbRef = FirebaseDatabase.getInstance().getReference("Teachers");

        btnUpdate.setOnClickListener(v -> {
            Intent updateIntent = new Intent(TeacherDetailActivity.this, AddTeacherActivity.class);
            updateIntent.putExtra("id", id);
            updateIntent.putExtra("name", name);
            updateIntent.putExtra("username", username);
            updateIntent.putExtra("password", password);
            updateIntent.putExtra("email", email);
            updateIntent.putExtra("phone", phone);
            updateIntent.putExtra("subject", subject);
            startActivity(updateIntent);
        });

        btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Teacher")
                    .setMessage("Are you sure you want to delete this teacher?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        dbRef.child(id).removeValue().addOnSuccessListener(unused -> {
                            Toast.makeText(this, "Teacher deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }).addOnFailureListener(e -> {
                            Toast.makeText(this, "Failed to delete", Toast.LENGTH_SHORT).show();
                        });
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}

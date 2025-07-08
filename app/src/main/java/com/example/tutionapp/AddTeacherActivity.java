package com.example.tutionapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.UUID;

public class AddTeacherActivity extends AppCompatActivity {

    EditText etName, etUsername, etPassword, etEmail, etPhone, etSubject;
    Button btnSave;
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);

        etName = findViewById(R.id.etTeacherName);
        etUsername = findViewById(R.id.etTeacherUsername);
        etPassword = findViewById(R.id.etTeacherPassword);
        etEmail = findViewById(R.id.etTeacherEmail);
        etPhone = findViewById(R.id.etTeacherPhone);
        etSubject = findViewById(R.id.etTeacherSubject);
        btnSave = findViewById(R.id.btnSaveTeacher);

        dbRef = FirebaseDatabase.getInstance().getReference("Teachers");

        btnSave.setOnClickListener(v -> {
            String id = UUID.randomUUID().toString();
            TeacherModel teacher = new TeacherModel(
                    id,
                    etName.getText().toString(),
                    etUsername.getText().toString(),
                    etPassword.getText().toString(),
                    etEmail.getText().toString(),
                    etPhone.getText().toString(),
                    etSubject.getText().toString()
            );

            dbRef.child(id).setValue(teacher).addOnSuccessListener(aVoid ->
                    Toast.makeText(this, "Teacher added", Toast.LENGTH_SHORT).show());
        });
    }
}

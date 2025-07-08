package com.example.tutionapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ManageStudentsActivity extends AppCompatActivity {

    RecyclerView recyclerStudents;
    Button btnAddStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_students);

        recyclerStudents = findViewById(R.id.recyclerStudents);
        btnAddStudent = findViewById(R.id.btnAddStudent);

        recyclerStudents.setLayoutManager(new LinearLayoutManager(this));

        // TODO: Load students from database (Firebase or SQLite)

        btnAddStudent.setOnClickListener(v -> {
            startActivity(new Intent(this, AddStudentActivity.class));
        });
    }
}

package com.example.tutionapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.*;

import java.util.ArrayList;
import java.util.List;

public class ManageTeachersActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TeacherAdapter adapter;
    List<TeacherModel> teacherList, filteredList;
    DatabaseReference dbRef;
    SearchView searchView;
    FloatingActionButton fabAddTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_teachers);

        recyclerView = findViewById(R.id.rvTeachers);
        searchView = findViewById(R.id.searchView);
        fabAddTeacher = findViewById(R.id.fabAddTeacher);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        teacherList = new ArrayList<>();
        filteredList = new ArrayList<>();
        adapter = new TeacherAdapter(this, filteredList);
        recyclerView.setAdapter(adapter);

        dbRef = FirebaseDatabase.getInstance().getReference("Teachers");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                teacherList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    TeacherModel teacher = ds.getValue(TeacherModel.class);
                    if (teacher != null) {
                        teacherList.add(teacher);
                    }
                }
                filterTeachers(searchView.getQuery().toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                filterTeachers(query);
                return true;
            }

            @Override public boolean onQueryTextChange(String newText) {
                filterTeachers(newText);
                return true;
            }
        });

        fabAddTeacher.setOnClickListener(v -> {
            Intent intent = new Intent(ManageTeachersActivity.this, AddTeacherActivity.class);
            startActivity(intent);
        });
    }

    private void filterTeachers(String query) {
        filteredList.clear();
        if (TextUtils.isEmpty(query)) {
            filteredList.addAll(teacherList);
        } else {
            String lower = query.toLowerCase();
            for (TeacherModel teacher : teacherList) {
                if (teacher.name.toLowerCase().contains(lower) ||
                        teacher.subject.toLowerCase().contains(lower)) {
                    filteredList.add(teacher);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }
}

package com.example.tutionapp;

/*
-- When showing a list of students in a RecyclerView,
    you need a way to convert each StudentModel into
    a visible UI element (a row or card).
-- The Adapter bridges between the data
    (your list of StudentModels) and the UI (RecyclerView).
    */


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<StudentModel> studentList;

    public StudentAdapter(Context context, List<StudentModel> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        StudentModel student = studentList.get(position);

        holder.tvStudentName.setText(student.name);
        holder.tvStudentUsername.setText(student.username);

        // On click open StudentDetailActivity with student data
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StudentDetailActivity.class);
            intent.putExtra("studentId", student.id);
            intent.putExtra("name", student.name);
            intent.putExtra("username", student.username);
            intent.putExtra("email", student.email);
            intent.putExtra("phone", student.phone);
            intent.putExtra("grade", student.grade);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView tvStudentName, tvStudentUsername;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvStudentName = itemView.findViewById(R.id.tvStudentName);
            tvStudentUsername = itemView.findViewById(R.id.tvStudentUsername);
        }
    }
}

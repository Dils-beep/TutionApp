/*
-- When showing a list of students in a RecyclerView,
    you need a way to convert each StudentModel into
    a visible UI element (a row or card).
-- The Adapter bridges between the data
    (your list of StudentModels) and the UI (RecyclerView).
    */

package com.example.tutionapp;

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

    Context context;
    List<StudentModel> studentList;

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
        holder.tvName.setText(student.name);
        holder.tvUsername.setText(student.username);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, StudentDetailActivity.class);
            intent.putExtra("studentId", student.id);
            intent.putExtra("name", student.name);
            intent.putExtra("username", student.username);
            intent.putExtra("password", student.password); // ✅ Added
            intent.putExtra("email", student.email);
            intent.putExtra("phone", student.phone);
            intent.putExtra("grade", student.grade);
            intent.putExtra("subjects", student.subjects);
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    static class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvUsername;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvStudentName);
            tvUsername = itemView.findViewById(R.id.tvStudentUsername);
        }
    }
}


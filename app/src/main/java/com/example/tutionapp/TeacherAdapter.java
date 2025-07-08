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

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder> {

    Context context;
    List<TeacherModel> teacherList;

    public TeacherAdapter(Context context, List<TeacherModel> teacherList) {
        this.context = context;
        this.teacherList = teacherList;
    }

    @NonNull
    @Override
    public TeacherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.teacher_item, parent, false);
        return new TeacherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherViewHolder holder, int position) {
        TeacherModel teacher = teacherList.get(position);
        holder.tvName.setText(teacher.name);
        holder.tvSubject.setText(teacher.subject);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, TeacherDetailActivity.class);
            intent.putExtra("id", teacher.id);
            intent.putExtra("name", teacher.name);
            intent.putExtra("username", teacher.username);
            intent.putExtra("password", teacher.password);
            intent.putExtra("email", teacher.email);
            intent.putExtra("phone", teacher.phone);
            intent.putExtra("subject", teacher.subject);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    static class TeacherViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvSubject;

        public TeacherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvTeacherName);
            tvSubject = itemView.findViewById(R.id.tvTeacherSubject);
        }
    }
}

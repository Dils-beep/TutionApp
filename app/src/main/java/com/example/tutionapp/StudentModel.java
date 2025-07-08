package com.example.tutionapp;

public class StudentModel {
    public String id;
    public String name;
    public String username;
    public String password;  // added password
    public String email;
    public String phone;
    public String grade;
    public String subjects;  // comma-separated subjects

    public StudentModel() {
        // Default constructor required for Firebase
    }

    public StudentModel(String id, String name, String username, String password, String email, String phone, String grade, String subjects) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;  // added password
        this.email = email;
        this.phone = phone;
        this.grade = grade;
        this.subjects = subjects;
    }
}

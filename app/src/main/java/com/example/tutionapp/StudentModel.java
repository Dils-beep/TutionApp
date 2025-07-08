package com.example.tutionapp;

public class StudentModel {
    public String id, name, username, email, phone, grade, password;

    public StudentModel() {} // Needed for Firebase

    public StudentModel(String id, String name, String username, String email,
                        String phone, String grade, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.grade = grade;
        this.password = password;
    }
}


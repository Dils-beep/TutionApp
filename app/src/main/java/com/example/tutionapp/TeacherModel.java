package com.example.tutionapp;

public class TeacherModel {
    public String id;
    public String name;
    public String username;
    public String password;
    public String email;
    public String phone;
    public String subject;

    public TeacherModel() {
    }

    public TeacherModel(String id, String name, String username, String password, String email, String phone, String subject) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.subject = subject;
    }
}

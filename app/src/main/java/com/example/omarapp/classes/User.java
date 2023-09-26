package com.example.omarapp.classes;

public class User {
    private String uid;
    private String password;
    private String email;
    private String username;

    public User(String uid,String email, String password,String username){
        this.email=email;
        this.password=password;
        this.uid=uid;
        this.username=username;

    }
}

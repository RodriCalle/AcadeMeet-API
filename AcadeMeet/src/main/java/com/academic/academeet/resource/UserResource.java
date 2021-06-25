package com.academic.academeet.resource;

public class UserResource{
    private Long id;
    private String first_name;
    private String last_name;
    private String mail;
    private String password;

    public Long getId() {
        return id;
    }

    public UserResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirst_name() {
        return first_name;
    }

    public UserResource setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public UserResource setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public UserResource setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserResource setPassword(String password) {
        this.password = password;
        return this;
    }
}

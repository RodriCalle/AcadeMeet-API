package com.academic.academeet.resource;


public class SaveUserResource {
    private String first_name;
    private String last_name;
    private String mail;
    private String password;

    public String getFirst_name() {
        return first_name;
    }

    public SaveUserResource setFirst_name(String first_name) {
        this.first_name = first_name;
        return this;
    }

    public String getLast_name() {
        return last_name;
    }

    public SaveUserResource setLast_name(String last_name) {
        this.last_name = last_name;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public SaveUserResource setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public SaveUserResource setPassword(String password) {
        this.password = password;
        return this;
    }
}

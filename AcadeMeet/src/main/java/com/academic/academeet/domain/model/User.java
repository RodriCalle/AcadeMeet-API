package com.academic.academeet.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_name;
    private String last_name;
    private String mail;
    private String password;

    public List<Plan> getPlans() {
        return plans;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "user_plans",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "plan_id")})
    private List<Plan> plans = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long user_id) {
        this.id = user_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isTaggedWith(Plan plan) {
        return this.getPlans().contains(plan);
    }

    public User tagWith(Plan plan) {
        if(!this.isTaggedWith(plan))
            this.getPlans().add(plan);
        return this;
    }

    public User untagWith(Plan plan) {
        if(!this.isTaggedWith(plan))
            this.getPlans().remove(plan);
        return this;
    }

}

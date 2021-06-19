package com.academic.academeet.domain.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "plans")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int price;
    @OneToMany
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long plan_id) {
        this.id = plan_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

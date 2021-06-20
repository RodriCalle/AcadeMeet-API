package com.academic.academeet.resource;

import javax.validation.constraints.NotNull;

public class SavePlanResource {
    @NotNull
    private String name;
    @NotNull
    private int price;

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

package com.academic.academeet.resource;

import lombok.Data;

import java.util.Date;

@Data
public class StudentResource extends UserResource {
    private Date bornDate;

    public Date getBornDate() {
        return bornDate;
    }

    public StudentResource setBornDate(Date bornDate) {
        this.bornDate = bornDate;
        return this;
    }
}

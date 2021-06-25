package com.academic.academeet.resource;

import lombok.Data;

import java.util.Date;

@Data
public class TutorResource extends UserResource {
    private Date hireDate;

    public Date getHireDate() {
        return hireDate;
    }

    public TutorResource setHireDate(Date hireDate) {
        this.hireDate = hireDate;
        return this;
    }
}

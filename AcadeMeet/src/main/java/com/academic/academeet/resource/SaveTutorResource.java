package com.academic.academeet.resource;

import lombok.Data;

import java.util.Date;

@Data
public class SaveTutorResource extends SaveUserResource{
    private Date hireDate;

    public Date getHireDate() {
        return hireDate;
    }

    public SaveTutorResource setHireDate(Date hireDate) {
        this.hireDate = hireDate;
        return this;
    }
}

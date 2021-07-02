package com.academic.academeet.resource;

import lombok.Data;

import java.util.Date;

@Data
public class SaveStudentResource extends SaveUserResource{
    private Date bornDate;

    public Date getBornDate() {
        return bornDate;
    }

    public SaveStudentResource setBornDate(Date bornDate) {
        this.bornDate = bornDate;
        return this;
    }
}

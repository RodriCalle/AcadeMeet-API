package com.academic.academeet.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "students")
public class Student extends User {
    private Date bornDate;

    public Date getBornDate() {
        return bornDate;
    }

    public Student setBornDate(Date bornDate) {
        this.bornDate = bornDate;
        return this;
    }

    //Comenta estas relaciones y funciona pero debes implementarlo en el controller pidiento pathVariable
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Level level;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private School school;

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}

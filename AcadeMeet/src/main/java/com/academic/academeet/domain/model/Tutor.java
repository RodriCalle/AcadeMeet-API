package com.academic.academeet.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode
@Data
@Entity
@Table(name = "tutors")
public class Tutor extends User{
    private Date hireDate;

    public Date getHireDate() {
        return hireDate;
    }

    public Tutor setHireDate(Date hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            mappedBy = "tutors")
    private List<Carrer> carrers = new ArrayList<>();

    public List<Carrer> getCarrers() {
        return carrers;
    }
}

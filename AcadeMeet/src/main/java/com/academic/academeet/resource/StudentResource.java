package com.academic.academeet.resource;

import lombok.Data;

@Data
public class StudentResource {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

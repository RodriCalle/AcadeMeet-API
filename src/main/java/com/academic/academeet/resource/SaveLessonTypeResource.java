package com.academic.academeet.resource;

public class SaveLessonTypeResource {
    private String name;

    private String description;

    private int students_quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStudents_quantity() {
        return students_quantity;
    }

    public void setStudents_quantity(int students_quantity) {
        this.students_quantity = students_quantity;
    }
}

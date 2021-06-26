package com.academic.academeet.resource;

public class LessonResource {
    private Long id;
    private String topic;

    public Long getId() {
        return id;
    }

    public LessonResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTopic() {
        return topic;
    }

    public LessonResource setTopic(String topic) {
        this.topic = topic;
        return this;
    }
}

package com.academic.academeet.resource;

public class NotificationResource {
    private Long id;
    private String linkMeeting;
    private String linkResources;
    private String content;


    public Long getId() {
        return id;
    }

    public NotificationResource setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLinkMeeting() {
        return linkMeeting;
    }

    public NotificationResource setLinkMeeting(String linkMeeting) {
        this.linkMeeting = linkMeeting;
        return this;
    }

    public String getLinkResources() {
        return linkResources;
    }

    public NotificationResource setLinkResources(String linkResources) {
        this.linkResources = linkResources;
        return this;
    }

    public String getContent() {
        return content;
    }

    public NotificationResource setContent(String content) {
        this.content = content;
        return this;
    }
}

package com.academic.academeet.resource;

public class NotificationResource {
    private Long id;

    private String linkMeeting;

    private String linkResources;

    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkMeeting() {
        return linkMeeting;
    }

    public void setLinkMeeting(String linkMeeting) {
        this.linkMeeting = linkMeeting;
    }

    public String getLinkResources() {
        return linkResources;
    }

    public void setLinkResources(String linkResources) {
        this.linkResources = linkResources;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
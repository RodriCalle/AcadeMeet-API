package com.academic.academeet.resource;

public class SaveNotificationResource {
    private String linkMeeting;
    private String linkResources;
    private String content;

    public String getLinkMeeting() {
        return linkMeeting;
    }

    public SaveNotificationResource setLinkMeeting(String linkMeeting) {
        this.linkMeeting = linkMeeting;
        return this;
    }

    public String getLinkResources() {
        return linkResources;
    }

    public SaveNotificationResource setLinkResources(String linkResources) {
        this.linkResources = linkResources;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SaveNotificationResource setContent(String content) {
        this.content = content;
        return this;
    }
}

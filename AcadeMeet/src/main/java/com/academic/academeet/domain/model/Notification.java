package com.academic.academeet.domain.model;

import javax.persistence.*;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String linkMeeting;

    private String linkResources;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private NotificationType notificationType;

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

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }
}

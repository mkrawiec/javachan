package com.javachan.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "messages")
public class Message extends AbstractEntity {

    @Column(name = "created_at")
    private Timestamp createdAt;

    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String body;

    private String attachment;

    @ManyToOne
    private Thread thread;

    public Message(String body, String attachment) {
        this.body = body;
        this.attachment = attachment;
    }

    public Message() {
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}

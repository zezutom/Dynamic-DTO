package com.example.model;

import com.example.view.annotation.Entity;
import com.example.view.annotation.View;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Entity
public class Message {

    @View("summary")
    @View("detail")
    private Long id;

    @View("summary")
    @View("detail")
    private LocalDate created;

    @View("summary")
    @View("detail")
    private String title;

    @View("summary")
    @View("detail")
    private User author;

    @View("detail")
    private List<User> recipients;

    @View("detail")
    private String body;

    public Message() {
        this.created = LocalDate.now();
    }

    public Message(Long id, String title, String body, User author, User... recipients) {
        this();
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.recipients = Arrays.asList(recipients);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public List<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<User> recipients) {
        this.recipients = recipients;
    }
}

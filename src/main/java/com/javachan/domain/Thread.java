package com.javachan.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "threads")
public class Thread extends AbstractEntity {

    @Size(min = 5, max = 255)
    private String title;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Valid
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "thread_id")
    private List<Message> messages = new ArrayList<>();

    @Transient
    private Message threadMessage;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Message getThreadMessage() {
        if (getMessages().isEmpty()) {
            setMessages(Collections.singletonList(new Message()));
        }

        return getMessages().get(0);
    }

    public void setThreadMessage(Message threadMessage) {
        getMessages().add(0, threadMessage);
        this.threadMessage = getThreadMessage();
    }

    public List<Message> getReplies() {
        return getMessages().subList(1, getMessages().size());
    }

    public void addReply(Message reply) {
        messages.add(reply);
    }

}
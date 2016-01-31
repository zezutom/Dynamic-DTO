package com.example.service;

import com.example.model.Message;
import com.example.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private List<Message> messages = new ArrayList<>();

    public MessageService() {
        User brian = new User(1L, "Brian", "Clozel", "bclozel@pivotal.io", "1 Jaures street", "69003", "Lyon", "France");
        User stephane = new User(2L, "Stéphane", "Nicoll", "snicoll@pivotal.io", "42 Obama street", "1000", "Brussel", "Belgium");
        User rossen = new User(3L, "Rossen", "Stoyanchev", "rstoyanchev@pivotal.io", "3 Warren street", "10011", "New York", "USA");

        Message info = new Message(1L, "Info", "This is an information message", brian, stephane, rossen);
        Message warning = new Message(2L, "Warning", "This is a warning message", stephane, rossen);
        Message alert = new Message(3L, "Alert", "This is an alert message", rossen, brian);

        messages.add(info);
        messages.add(warning);
        messages.add(alert);
    }

    public List<Message> getAll() {
        return messages;
    }

    public Message get(Long id) {
        return this.messages.stream().filter((m) -> m.getId() == id).findFirst().get();
    }

    public Message create(Message message) {
        this.messages.add(message);
        return message;
    }
}

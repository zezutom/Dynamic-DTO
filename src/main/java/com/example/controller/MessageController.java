package com.example.controller;

import com.example.model.Message;
import com.example.service.MessageService;
import com.example.view.ViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("/")
    public List<Message> getAllMessages() {
        return messageService.getAll()
                .stream()
                .map(m -> new ViewMapper<Message>().map(m, "summary"))
                .collect(Collectors.toList());
    }

    @RequestMapping("/{id}")
    public Message getMessage(@PathVariable Long id) {
        return new ViewMapper<Message>().map(messageService.get(id), "detail");
    }
}

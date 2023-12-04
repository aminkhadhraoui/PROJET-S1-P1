package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.Message;
import com.Projet.Projet.Services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
@CrossOrigin(origins = "*")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }
    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable("id") Long messageId){
        return messageService.getMessageById(messageId);
    }
    @PostMapping("/add")
    public Message addMessage(@RequestBody Message message){
        return messageService.addMessage(message);
    }
    @PutMapping("/update")
    public Message updateMessage(@RequestBody Message message){
        return messageService.updateMessage(message);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteMessage(@PathVariable("id") Long messageId){
        messageService.deleteMessage(messageId);
    }
    @GetMapping("/user/{uid}/message/{mid}")
    public Message addMessageToUser(@PathVariable("uid") Long userId, @PathVariable("mid") Long messageId){
        return messageService.addMessageToUser(userId,messageId);
    }
}

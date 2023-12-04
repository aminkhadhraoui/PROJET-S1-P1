package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Message;

import java.util.List;

public interface MessageService {

    Message addMessage(Message message);
    List<Message> getAllMessages();
    Message getMessageById(Long messageId);
    Message updateMessage(Message message);
    void deleteMessage(Long messageId);
    Message addMessageToUser(Long userId, Long messageId);
}

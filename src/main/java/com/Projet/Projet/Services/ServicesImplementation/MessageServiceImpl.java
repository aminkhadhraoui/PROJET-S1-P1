package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Message;
import com.Projet.Projet.Entities.User;
import com.Projet.Projet.Repositories.MessageRepository;
import com.Projet.Projet.Repositories.UserRepository;
import com.Projet.Projet.Services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    @Override
    public Message addMessage(Message message) {
        message.setDate(LocalDate.now());
        message.setHeure(String.valueOf(LocalTime.now()));
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message getMessageById(Long messageId) {
        return messageRepository.findById(messageId).orElseThrow(
                () -> new NoSuchElementException("Aucun message avec ID: "+messageId)
        );
    }

    @Override
    public Message updateMessage(Message message) {
        if (!messageRepository.existsById(message.getId())){
            throw new NoSuchElementException("Aucun message avec ID: "+message.getId());
        }
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessage(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    @Override
    public Message addMessageToUser(Long userId, Long messageId) {
        Message message = getMessageById(messageId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("Aucun user avec ID: "+userId)
        );
        message.setUser(user);
        updateMessage(message);
        user.getMessages().add(message);
        userRepository.save(user);
        return message;
    }
}

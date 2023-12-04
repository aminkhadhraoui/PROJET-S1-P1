package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Discussion;
import com.Projet.Projet.Entities.Message;
import com.Projet.Projet.Entities.User;
import com.Projet.Projet.Repositories.DiscussionRepository;
import com.Projet.Projet.Repositories.UserRepository;
import com.Projet.Projet.Services.DiscussionService;
import com.Projet.Projet.Services.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class DiscussionServiceImpl implements DiscussionService {
    private DiscussionRepository discussionRepository;
    private UserRepository userRepository;
    private MessageService messageService;
    @Override
    public Discussion addDiscussion(Discussion discussion) {
        return discussionRepository.save(discussion);
    }

    @Override
    public List<Discussion> getAllDiscussion() {
        return discussionRepository.findAll();
    }

    @Override
    public Discussion getDiscussionById(Long discussionId) {
        return discussionRepository.findById(discussionId).orElseThrow(
                () -> new NoSuchElementException("Aucune discussion avec ID: "+discussionId)
        );
    }

    @Override
    public Discussion updateDiscussion(Discussion discussion) {
        if (!discussionRepository.existsById(discussion.getId())){
            throw new NoSuchElementException("Aucune discussion avec ID: "+discussion.getId());
        }
        return discussionRepository.save(discussion);
    }

    @Override
    public void deleteDiscussion(Long discussionId) {
        discussionRepository.deleteById(discussionId);
    }

    @Override
    public Discussion addMessageToDiscussion(Long messageId, Long discussionId) {
        Discussion discussion = getDiscussionById(discussionId);
        Message message = messageService.getMessageById(messageId);
        discussion.getMessages().add(message);
        updateDiscussion(discussion);
        return discussion;
    }

    @Override
    public Discussion addUserToDiscussion(Long userId, Long discussionId) {
        Discussion discussion = getDiscussionById(discussionId);
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("Aucun user avec ID: "+userId)
        );
        discussion.getUsers().add(user);
        updateDiscussion(discussion);
        user.getDiscussions().add(discussion);
        userRepository.save(user);
        return discussion;
    }
}

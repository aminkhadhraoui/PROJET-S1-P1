package com.Projet.Projet.Services;

import com.Projet.Projet.Entities.Discussion;

import java.util.List;

public interface DiscussionService {

    Discussion addDiscussion(Discussion discussion);
    List<Discussion> getAllDiscussion();
    Discussion getDiscussionById(Long discussionId);
    Discussion updateDiscussion(Discussion discussion);
    void deleteDiscussion(Long discussionId);
    Discussion addMessageToDiscussion(Long messageId, Long discussionId);
    Discussion addUserToDiscussion(Long userId, Long discussionId);
}

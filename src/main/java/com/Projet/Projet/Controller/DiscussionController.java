package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.Discussion;
import com.Projet.Projet.Services.DiscussionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discussion")
@CrossOrigin(origins = "*")
public class DiscussionController {
    @Autowired
    private DiscussionService discussionService;
    @GetMapping("")
    public List<Discussion> getAllDiscussions(){
        return discussionService.getAllDiscussion();
    }
    @GetMapping("/{id}")
    public Discussion getDiscussionById(@PathVariable("id") Long discussionId){
        return discussionService.getDiscussionById(discussionId);
    }
    @PostMapping("/add")
    public Discussion addDiscussion(@RequestBody Discussion discussion){
        return discussionService.addDiscussion(discussion);
    }
    @PutMapping("/update")
    public Discussion updateDiscussion(@RequestBody Discussion discussion){
        return discussionService.updateDiscussion(discussion);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteDiscussion(@PathVariable("id") Long discussionId){
        discussionService.deleteDiscussion(discussionId);
    }
    @GetMapping("/message/{mid}/discussion/{did}")
    public Discussion addMessageToDiscussion(@PathVariable("mid") Long messageId, @PathVariable("did") Long discussionId){
        return discussionService.addMessageToDiscussion(messageId,discussionId);
    }
    @GetMapping("/user/{uid}/discussion/{did}")
    public Discussion addUserToDiscussion(@PathVariable("uid") Long userId, @PathVariable("did") Long discussionId){
        return discussionService.addUserToDiscussion(userId,discussionId);
    }
}

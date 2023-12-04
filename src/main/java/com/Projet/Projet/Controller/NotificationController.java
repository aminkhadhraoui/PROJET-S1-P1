package com.Projet.Projet.Controller;

import com.Projet.Projet.Entities.Notification;
import com.Projet.Projet.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("")
    List<Notification> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @GetMapping("/{id}")
    Notification getNotificationById(@PathVariable Long id){
        return notificationService.getNotificationById(id);
    }

    @PostMapping("/add")
    Notification addNotification (@RequestBody Notification notification){
        return notificationService.addNotification(notification);
    }

    @PutMapping("/edit/{id}")
    Notification editNotification(@PathVariable Long id, @RequestBody Notification notification){
        return notificationService.editNotification(id,notification);
    }

    @DeleteMapping("/delete/{id}")
    void deleteNotification (@PathVariable Long id){
        notificationService.deleteNotification(id);
    }
}

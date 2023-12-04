package com.Projet.Projet.Services.ServicesImplementation;

import com.Projet.Projet.Entities.Notification;
import com.Projet.Projet.Repositories.NotificationRepository;
import com.Projet.Projet.Services.NotificationService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public List<Notification> getAllNotifications(){
        return notificationRepository.findAll();
    }

    @Override
    public Notification getNotificationById(Long id){
        return notificationRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("aucune notification trouvée"));
    }

    @Override
    public Notification addNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    @Override
    public Notification editNotification(Long id, Notification notification){
        Notification notificationDB = getNotificationById(id);

        notificationDB.setContenu(notification.getContenu());
        notificationDB.setEtat(notification.getEtat());
        notificationDB.setDate(notification.getDate());

        notificationRepository.save(notificationDB);

        return notificationDB;
    }

    @Override
    public void deleteNotification(Long id){
        notificationRepository.deleteById(id);
    }
}

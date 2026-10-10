package com.punia.notification.repository;

import com.punia.notification.entities.Notification;

import java.util.Optional;

public interface NotificationRepository {
    void save(Notification notification);
    Optional<Notification> findById(String id);
}

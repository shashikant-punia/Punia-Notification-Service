package com.punia.notification.repository.impl;

import com.punia.notification.entities.Notification;
import com.punia.notification.repository.NotificationRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryNotificationRepository implements NotificationRepository {
    private final Map<String, Notification> store = new ConcurrentHashMap<>();

    @Override
    public void save(Notification notification) {
        store.put(notification.getId(), notification);
    }

    @Override
    public Optional<Notification> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }
}
